package com.github.jaewookmun.mybatisdynamicsql.mapper;

import com.github.jaewookmun.mybatisdynamicsql.domain.PersonRecord;
import com.github.jaewookmun.mybatisdynamicsql.support.PersonDynamicSqlSupport;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

import java.util.List;
import java.util.Optional;

import static com.github.jaewookmun.mybatisdynamicsql.support.PersonDynamicSqlSupport.person;

@Mapper
public interface PersonMapper extends CommonCountMapper, CommonInsertMapper<PersonRecord>, CommonUpdateMapper {

    BasicColumn[] selectList = BasicColumn.columnList(PersonDynamicSqlSupport.id, PersonDynamicSqlSupport.firstName, PersonDynamicSqlSupport.lastName, PersonDynamicSqlSupport.birthDate, PersonDynamicSqlSupport.employed, PersonDynamicSqlSupport.occupation, PersonDynamicSqlSupport.addressId);

    default int insert(PersonRecord row) {
        return MyBatis3Utils.insert(this::insert, row, person, c -> c
                    .map(PersonDynamicSqlSupport.id).toProperty("id")
                    .map(PersonDynamicSqlSupport.firstName).toProperty("firstName")
                    .map(PersonDynamicSqlSupport.lastName).toProperty("lastName")
                    .map(PersonDynamicSqlSupport.birthDate).toProperty("birthDate")
                    .map(PersonDynamicSqlSupport.employed).toProperty("employed")
                    .map(PersonDynamicSqlSupport.occupation).toProperty("occupation")
                    .map(PersonDynamicSqlSupport.addressId).toProperty("addressId")
        );
    }
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PersonResult", value= {
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="first_name", property="firstName", jdbcType=JdbcType.VARCHAR),
            @Result(column="last_name", property="lastName", jdbcType=JdbcType.VARCHAR),
            @Result(column="birth_date", property="birthDate", jdbcType=JdbcType.DATE),
            @Result(column="employed", property="employed", jdbcType=JdbcType.VARCHAR),
            @Result(column="occupation", property="occupation", jdbcType=JdbcType.VARCHAR)
    })
    List<PersonRecord> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PersonResult")
    Optional<PersonRecord> selectOne(SelectStatementProvider selectStatement);

    default Optional<PersonRecord> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, person, completer);
    }

    default Optional<PersonRecord> findById(Integer recordId) {
        return selectOne(c -> c.where(PersonDynamicSqlSupport.id, SqlBuilder.isEqualTo(recordId)));
    }

    default List<PersonRecord> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, person, completer);
    }

    default List<PersonRecord> findAll() {
        return select(SelectDSLCompleter.allRows());
    }
}
