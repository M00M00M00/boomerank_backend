package boomerank.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@ToString
@AllArgsConstructor
public class PageResponseDto {
    private int totalPage;
    private long totalElements;
    private List<Map<String, Object>> retList;
}
