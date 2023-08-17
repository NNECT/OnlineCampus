package com.education.onlinecampus.data.marker;

/**
 * DTO 마커 인터페이스
 */
public interface DTOMarker<E extends EntityMarker> {
    /**
     * DTO 객체를 Entity 객체로 변환
     * @return Entity 객체
     */
    E toEntity();
}
