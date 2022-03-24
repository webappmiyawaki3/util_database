package connect;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SampleObject {
    private String name;
    private String text;

    @Override
    public String toString(){
        return String.format("name:%s text:%s",this.name,this.text);
    }
}
