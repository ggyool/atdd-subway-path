package wooteco.subway.station.ui;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wooteco.subway.station.application.StationService;
import wooteco.subway.station.domain.Station;
import wooteco.subway.station.dto.StationRequest;
import wooteco.subway.station.dto.StationResponse;

@RestController
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/stations/{id}")
    public ResponseEntity<StationResponse> findStation(@PathVariable Long id) {
        return ResponseEntity.ok(
            StationResponse.of(stationService.findStationById(id))
        );
    }

    @PostMapping("/stations")
    public ResponseEntity<StationResponse> createStation(@Valid @RequestBody StationRequest stationRequest) {
        StationResponse stationResponse = stationService.saveStation(stationRequest);
        URI createdUri = URI.create("/stations/" + stationResponse.getId());
        return ResponseEntity.created(createdUri)
            .body(stationResponse);
    }

    @GetMapping("/stations")
    public ResponseEntity<List<StationResponse>> showStations() {
        return ResponseEntity.ok()
            .body(stationService.findAllStationResponses());
    }

    @DeleteMapping("/stations/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        stationService.deleteStationById(id);
        return ResponseEntity.noContent()
            .build();
    }
}
