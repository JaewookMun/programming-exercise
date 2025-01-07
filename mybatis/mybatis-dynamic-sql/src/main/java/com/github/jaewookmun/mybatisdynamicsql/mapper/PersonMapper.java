package com.github.jaewookmun.mybatisdynamicsql.mapper;

import com.github.jaewookmun.mybatisdynamicsql.domain.PersonRecord;
import com.github.jaewookmun.mybatisdynamicsql.support.PersonDynamicSqlSupport;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

import static com.github.jaewookmun.mybatisdynamicsql.support.PersonDynamicSqlSupport.person;

@Mapper
public interface PersonMapper extends CommonCountMapper, CommonInsertMapper<PersonRecord>, CommonUpdateMapper {

    default int insert(PersonRecord row) {
        return MyBatis3Utils.insert(this::insert, row, person, c ->
                    c.map(PersonDynamicSqlSupport.id).toProperty("id")
                    .map(PersonDynamicSqlSupport.firstName).toProperty("firstName")
                    .map(PersonDynamicSqlSupport.lastName).toProperty("lastName")
                    .map(PersonDynamicSqlSupport.birthDate).toProperty("birthDate")
                    .map(PersonDynamicSqlSupport.employed).toProperty("employed")
                    .map(PersonDynamicSqlSupport.occupation).toProperty("occupation")
                    .map(PersonDynamicSqlSupport.addressId).toProperty("addressId")
        );
    }
}
