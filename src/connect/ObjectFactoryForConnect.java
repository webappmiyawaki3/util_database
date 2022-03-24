package connect;

import java.util.Random;
import java.util.stream.IntStream;

public class ObjectFactoryForConnect {
    public static SampleObject create() {
        int num = new Random().nextInt(1000);
        return SampleObject.builder()
                .name("sample"+num)
                .text("sampleText")
                .build();
    }
}
