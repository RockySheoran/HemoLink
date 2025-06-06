package com.project.hemolink.matching_service.repositories;

import com.project.hemolink.matching_service.entities.MatchLog;
import com.project.hemolink.matching_service.entities.enums.NotificationStatus;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface MatchLogRepository extends JpaRepository<MatchLog, UUID> {

    List<MatchLog> findByRequestIdAndStatus(String requestId, NotificationStatus status);

    List<MatchLog> findByStatus(NotificationStatus status);

    List<MatchLog> findByDonorId(String donorId);

    List<MatchLog> findByDonorIdAndStatusNot(String donorId, NotificationStatus notificationStatus);

    List<MatchLog> findByRequestId(String requestId);

    long countByRequestId(String requestId);
}
