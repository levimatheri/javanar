import javax.sound.sampled.*;
import java.io.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Splitter {
    private static final Logger LOGGER = Logger.getLogger(Splitter.class.getName());
    private AudioInputStream inputStream;
    private AudioFormat audioFormat;
    private String _filePath;

    public Splitter(String filePath) {
        _filePath = filePath;
        ConsoleHandler handler = new ConsoleHandler();
        LOGGER.addHandler(handler);

        buildInputStream(_filePath);
        audioFormat = inputStream.getFormat();
    }

    /**
     * Builds an AudioInputStream object from the file path of the wav file.
     * @param filePath The file path of the wav file.
     */
    private void buildInputStream(String filePath) {
        try {
            inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(filePath)));
        } catch (UnsupportedAudioFileException | IOException uafe) {
            LOGGER.log(Level.SEVERE, uafe.toString(), uafe);
        }
    }

    private void split(int chunkSize, String directoryPath) {
        try {
            // get byte size per millisecond
            int bitsPerSample = audioFormat.getSampleSizeInBits();
            float sampleRate = audioFormat.getSampleRate();
            float bytesPerMilliSecond = ((bitsPerSample * sampleRate * audioFormat.getChannels()) / 8) / 1000;
            int chunkBytes = (int) bytesPerMilliSecond * chunkSize;

            File directory;
            if (directoryPath != null && !directoryPath.isEmpty()) {
                directory = new File(directoryPath);
            }
            else {
                directory = new File("./src/main/resources/test");
            }

            if (!directory.exists())
                directory.mkdir();
            int numberOfBytesRead;
            byte[] buffer = new byte[chunkBytes];
            int chunkIndex = 0;

            while ((numberOfBytesRead = inputStream.read(buffer)) != -1) {
                try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                    outputStream.write(buffer, 0, numberOfBytesRead);

                    ByteArrayInputStream b_in = new ByteArrayInputStream(outputStream.toByteArray());
                    AudioInputStream ais = new AudioInputStream(b_in, audioFormat, buffer.length / audioFormat.getFrameSize());
                    AudioSystem.write(ais, AudioSystem.getAudioFileFormat(new File(_filePath)).getType(),
                            new File(directory + "/test_" +  chunkIndex + ".wav"));

                    chunkIndex++;

                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException ie) {
            LOGGER.log(Level.SEVERE, ie.toString(), ie);
        }
    }

    public static void main(String[] args) {
        Splitter splitter = new Splitter("E:\\music_transcription_project\\wav_files\\CominThroTheRye.wav");

        splitter.split(3000, null);
    }
}
