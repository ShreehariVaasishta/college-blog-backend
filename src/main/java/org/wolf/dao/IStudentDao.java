package org.wolf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.wolf.dto.ListStudentDTO;
import org.wolf.dto.StudentDTO;
import org.wolf.entity.Student;

@Repository
public interface IStudentDao extends JpaRepository<Student, String> {

	boolean existsByUserName(@Param("userName") String userName);

	@Query("select new org.wolf.dto.StudentDTO(s.firstName, s.lastName, s.userName, s.password, s.registrationNumber, s.collegeCode.collegeCode ) from Student s where s.userName like :userName order by s.registrationNumber")
	StudentDTO findByUserName(@Param("userName") String userName);

	@Query("select new org.wolf.dto.ListStudentDTO(CONCAT(s.firstName,' ', s.lastName), s.userName, s.registrationNumber, s.collegeCode.collegeCode ) from Student s where s.registrationNumber like :USN order by s.registrationNumber")
	ListStudentDTO findByUSN(@Param("USN") String studentUSN);

	@Query("select new org.wolf.dto.ListStudentDTO(CONCAT(s.firstName,' ', s.lastName), s.userName, s.registrationNumber, s.collegeCode.collegeCode ) from Student s order by s.registrationNumber")
	List<ListStudentDTO> listAll();

	@Query("select new org.wolf.dto.ListStudentDTO(CONCAT(s.firstName,' ', s.lastName), s.userName, s.registrationNumber, s.collegeCode.collegeCode ) from Student s where s.collegeCode.collegeCode like :clgCode order by s.registrationNumber")
	List<ListStudentDTO> listAllByCollegeCode(@Param("clgCode") String clgCode);

}
