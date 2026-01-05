package com.sudeshkar.SmartWasteManagement.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sudeshkar.SmartWasteManagement.dto.AnalyticsWeightDTO;
import com.sudeshkar.SmartWasteManagement.dto.TimeSeriesDTO;
import com.sudeshkar.SmartWasteManagement.model.WasteCollectionLog;

@Repository
public interface WasteCollectionLogRepository extends JpaRepository<WasteCollectionLog, Long> {
	@Query("SELECT new com.sudeshkar.SmartWasteManagement.dto.AnalyticsWeightDTO(z.zoneName, SUM(l.wasteWeight)) " +
		       "FROM WasteCollectionLog l JOIN l.bin b JOIN b.zone z " +
		       "GROUP BY z.zoneName")
		List<AnalyticsWeightDTO> getWasteVolumeByZone();

    // 2. Line Chart Data: Count of collections per day for the last 7 days
	@Query("SELECT new com.sudeshkar.SmartWasteManagement.dto.TimeSeriesDTO(" +
		       "CAST(function('to_char', l.collectedAt, 'YYYY-MM-DD') AS string), COUNT(l)) " +
		       "FROM WasteCollectionLog l " +
		       "WHERE l.collectedAt >= :startDate " +
		       "GROUP BY function('to_char', l.collectedAt, 'YYYY-MM-DD') " +
		       "ORDER BY function('to_char', l.collectedAt, 'YYYY-MM-DD') ASC")
		List<TimeSeriesDTO> getWeeklyCollectionTrend(@Param("startDate") LocalDateTime startDate);
}
