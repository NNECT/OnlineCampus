package com.education.onlinecampus.service.common.impl;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.data.entity.*;
import com.education.onlinecampus.data.mapper.*;
import com.education.onlinecampus.data.marker.DTOMarker;
import com.education.onlinecampus.data.marker.EntityMarker;
import com.education.onlinecampus.repository.FileRepository;
import com.education.onlinecampus.repository.MemberRepository;
import com.education.onlinecampus.service.common.RepositoryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;

    @Override public MemberRepository getMemberRepository(){return memberRepository;}
    @Override public FileRepository getFileRepository() {return fileRepository;}

    @Override
    public <E extends EntityMarker, T extends DTOMarker> E convertDTOToEntity(T dto) {
        if (dto instanceof CommonCodeDTO)
            return (E) CommonCodeMapper.INSTANCE.toEntity((CommonCodeDTO) dto);
        if (dto instanceof FileDTO)
            return (E) FileMapper.INSTANCE.toEntity((FileDTO) dto);
        if (dto instanceof MemberDTO)
            return (E) MemberMapper.INSTANCE.toEntity((MemberDTO) dto);
        return null;
    }

    @Override
    public <E extends EntityMarker, T extends DTOMarker> T convertEntityToDTO(E entity) {
        if (entity instanceof CommonCode)
            return (T) CommonCodeMapper.INSTANCE.toDTO((CommonCode) entity);
        if (entity instanceof File)
            return (T) FileMapper.INSTANCE.toDTO((File) entity);
        if (entity instanceof Member)
            return (T) MemberMapper.INSTANCE.toDTO((Member) entity);
        return null;
    }
}
