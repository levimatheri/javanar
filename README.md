# javanar
> A java library for splitting audio wav files into chunks

Takes an audio wav file and splits it into chunks based on milliseconds per chunk. It then dumps the chunks into a defined output directory or a default output directory.

![](header.png)

## Requirements
Java

## Usage example

```
public static void main(String[] args) {
    Splitter splitter = new Splitter("E:\\wav_files\\CominThroTheRye.wav");

    splitter.split(3000, null);
}
```

## Contributing

1. Fork it (<https://github.com/levimatheri/javanar>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request
