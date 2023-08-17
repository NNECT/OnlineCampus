package com.education.onlinecampus.data.marker;

/**
 * Entity 마커 인터페이스
 */
public interface EntityMarker<D extends DTOMarker> {
    /**
     * Entity 객체를 DTO 객체로 변환
     * @return DTO 객체
     */
    D toDTO();
}
