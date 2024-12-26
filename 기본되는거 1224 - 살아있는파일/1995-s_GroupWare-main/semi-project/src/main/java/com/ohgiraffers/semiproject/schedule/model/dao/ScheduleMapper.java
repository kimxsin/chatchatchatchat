package com.ohgiraffers.semiproject.schedule.model.dao;

import com.ohgiraffers.semiproject.schedule.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ScheduleMapper {

    // 출근시간 저장
    void insertWorkStartTime(ScheduleDTO scheduleDTO);

    // 퇴근시간 저장
    void insertWorkEndTime(ScheduleDTO scheduleDTO);

    List<ScheduleDTO> getSchedulesByUserCode(String userCode);

    CheckInResponseDTO getCheckInTime(String userCode, LocalDateTime localDateTime);

    CheckOutResponseDTO getCheckOutTime(String userCode, LocalDateTime localDateTime);

    VacationDTO getVacation(String userCode);

    ScheduleDTO getAttendanceByWorkStartTime(String userCode, String date);

    ScheduleDTO getAttendanceByWorkEndTime(String userCode, String date);

    void modifyAttendanceRequest(ScheduleDTO attendanceModifyInfo);

    List<ScheduleDTO> getAllAttendanceRequests(String userCode);

    void deleteAttendanceRequest(ScheduleDTO deleteAttendance);
}
