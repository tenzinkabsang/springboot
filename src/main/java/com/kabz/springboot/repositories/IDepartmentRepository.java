package com.kabz.springboot.repositories;

import com.kabz.springboot.entities.Department;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Long> {

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Transactional
    @Query("delete from Department d where d.id = :departmentId")
    Integer deleteCustom(@Param("departmentId") Long id);

    Department findDepartmentByNameIgnoreCase(String name);
}
