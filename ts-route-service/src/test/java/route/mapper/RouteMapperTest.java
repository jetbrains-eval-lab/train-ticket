package route.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import route.entity.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestRouteMapperConfiguration.class})
public class RouteMapperTest {

    @Autowired
    private RouteMapper routeMapper;

    @Test
    public void testToDtoWithValidRoute() {
        List<String> stations = Arrays.asList("Station1", "Station2", "Station3");
        List<Integer> distances = Arrays.asList(10, 20, 30);
        Route route = new Route("test-id", stations, distances, "Station1", "Station3");

        RouteDto result = routeMapper.toDto(route);

        Assert.assertNotNull(result);
        Assert.assertEquals("test-id", result.getId());
        Assert.assertEquals(stations, result.getStations());
        Assert.assertEquals(distances, result.getDistances());
        Assert.assertEquals("Station1", result.getStartStation());
        Assert.assertEquals("Station3", result.getEndStation());
    }

    @Test
    public void testToDtoWithNullRoute() {
        RouteDto result = routeMapper.toDto(null);
        Assert.assertNull(result);
    }

    @Test
    public void testToDtoListWithValidRoutes() {
        List<String> stations1 = Arrays.asList("A", "B");
        List<Integer> distances1 = Arrays.asList(5, 10);
        Route route1 = new Route("id1", stations1, distances1, "A", "B");

        List<String> stations2 = Arrays.asList("C", "D");
        List<Integer> distances2 = Arrays.asList(15, 20);
        Route route2 = new Route("id2", stations2, distances2, "C", "D");

        List<Route> routes = Arrays.asList(route1, route2);

        List<RouteDto> result = routeMapper.toDtoList(routes);

        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
        
        RouteDto dto1 = result.get(0);
        Assert.assertEquals("id1", dto1.getId());
        Assert.assertEquals(stations1, dto1.getStations());
        Assert.assertEquals(distances1, dto1.getDistances());
        
        RouteDto dto2 = result.get(1);
        Assert.assertEquals("id2", dto2.getId());
        Assert.assertEquals(stations2, dto2.getStations());
        Assert.assertEquals(distances2, dto2.getDistances());
    }

    @Test
    public void testToDtoListWithNullList() {
        List<RouteDto> result = routeMapper.toDtoList(null);
        Assert.assertNull(result);
    }

    @Test
    public void testToDtoListWithEmptyList() {
        List<Route> routes = new ArrayList<>();
        List<RouteDto> result = routeMapper.toDtoList(routes);
        
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testToEntityWithValidDto() {
        List<String> stations = Arrays.asList("Station1", "Station2", "Station3");
        List<Integer> distances = Arrays.asList(10, 20, 30);
        RouteDto routeDTO = new RouteDto("test-id", stations, distances, "Station1", "Station3");

        Route result = routeMapper.toEntity(routeDTO);

        Assert.assertNotNull(result);
        Assert.assertEquals("test-id", result.getId());
        Assert.assertEquals(stations, result.getStations());
        Assert.assertEquals(distances, result.getDistances());
        Assert.assertEquals("Station1", result.getStartStation());
        Assert.assertEquals("Station3", result.getEndStation());
    }

    @Test
    public void testToEntityWithNullDto() {
        Route result = routeMapper.toEntity(null);
        Assert.assertNull(result);
    }

    @Test
    public void testRoundTripConversion() {
        List<String> stations = Arrays.asList("X", "Y", "Z");
        List<Integer> distances = Arrays.asList(100, 200, 300);
        Route originalRoute = new Route("round-trip-id", stations, distances, "X", "Z");

        RouteDto dto = routeMapper.toDto(originalRoute);
        Route convertedRoute = routeMapper.toEntity(dto);

        Assert.assertEquals(originalRoute.getId(), convertedRoute.getId());
        Assert.assertEquals(originalRoute.getStations(), convertedRoute.getStations());
        Assert.assertEquals(originalRoute.getDistances(), convertedRoute.getDistances());
        Assert.assertEquals(originalRoute.getStartStation(), convertedRoute.getStartStation());
        Assert.assertEquals(originalRoute.getEndStation(), convertedRoute.getEndStation());
    }
}