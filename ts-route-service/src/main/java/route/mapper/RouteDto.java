package route.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {
    private String id;
    private List<String> stations;
    private List<Integer> distances;
    private String startStation;
    private String endStation;
}
