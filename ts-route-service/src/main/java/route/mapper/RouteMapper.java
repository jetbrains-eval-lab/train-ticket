package route.mapper;

import org.mapstruct.Mapper;
import route.entity.Route;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    RouteDto toDto(Route route);

    List<RouteDto> toDtoList(List<Route> routes);

    Route toEntity(RouteDto routeDto);
}
