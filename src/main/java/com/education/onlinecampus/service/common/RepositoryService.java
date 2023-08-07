package com.education.onlinecampus.service.common;

import com.education.onlinecampus.data.marker.DTOMarker;
import com.education.onlinecampus.data.marker.EntityMarker;

/**
 * RepositoryService 인터페이스
 * Repository 관련 공통 기능을 정의한다.
 * 모든 서비스는 Repository를 사용할 때 직접 주입받지 않고 이 서비스의 Getter 메서드를 통해 사용해야 한다.
 * 또한 DTO와 Entity 상호간 변환을 위한 메서드를 정의한다.
 */
public interface RepositoryService {
    /**
     * DTO 객체를 Entity 객체로 변환
     * @param dto DTO 객체
     * @return Entity 객체
     * @param <E> Entity 객체
     * @param <T> DTO 객체
     */
    <E extends EntityMarker, T extends DTOMarker> E convertDTOToEntity(T dto);

    /**
     * Entity 객체를 DTO 객체로 변환
     * @param entity Entity 객체
     * @return DTO 객체
     * @param <E> Entity 객체
     * @param <T> DTO 객체
     */
    <E extends EntityMarker, T extends DTOMarker> T convertEntityToDTO(E entity);
}
