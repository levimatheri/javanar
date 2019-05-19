/**
 * Will hold the immutable audio object and properties that can be manipulated
 */
public class AudioSegment {
  private final double sampleWidth;
  private final double frameRate;
  private final int channels;
  private int frameWidth;
  private String filePath;
  public AudioSegment(AudioSegmentBuilder builder) {
    this.sampleWidth = builder.sampleWidth;
    this.frameRate = builder.frameRate;
    this.channels = builder.channels;

    if (sampleWidth != 0.0) {

    }
  }

  /**
   * Builds an audio segment object given specific properties.
   */
  public static class AudioSegmentBuilder {

    private double sampleWidth;
    private double frameRate;
    private int channels;

    public AudioSegmentBuilder sampleWidth(double width) {
      this.sampleWidth = width;
      return this;
    }

    public AudioSegmentBuilder frameRate(double rate) {
      this.frameRate = rate;
      return this;
    }

    public AudioSegmentBuilder channels(int channels) {
      this.channels = channels;
      return this;
    }
  }
}
