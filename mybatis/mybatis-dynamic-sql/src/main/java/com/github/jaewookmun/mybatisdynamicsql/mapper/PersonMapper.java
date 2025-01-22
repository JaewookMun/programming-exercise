package com.github.jaewookmun.mybatisdynamicsql.mapper;

import com.github.jaewookmun.mybatisdynamicsql.domain.PersonRecord;
import com.github.jaewookmun.mybatisdynamicsql.support.PersonDynamicSqlSupport;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.*;

import java.util.List;
import java.util.Optional;

import static com.github.jaewookmun.mybatisdynamicsql.support.PersonDynamicSqlSupport.*;

@Mapper
public interface PersonMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<PersonRecord>, CommonUpdateMapper {

    BasicColumn[] selectList = BasicColumn.columnList(PersonDynamicSqlSupport.id, PersonDynamicSqlSupport.firstName, lastName, PersonDynamicSqlSupport.birthDate, PersonDynamicSqlSupport.employed, PersonDynamicSqlSupport.occupation, PersonDynamicSqlSupport.addressId);

    default int insert(PersonRecord row) {
        return MyBatis3Utils.insert(this::insert, row, person, c -> c
                    .map(PersonDynamicSqlSupport.id).toProperty("id")
                    .map(PersonDynamicSqlSupport.firstName).toProperty("firstName")
                    .map(lastName).toProperty("lastName")
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
    @Deprecated
    Optional<PersonRecord> selectOne(SelectStatementProvider selectStatement);

    @Deprecated
    default Optional<PersonRecord> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, person, completer);
    }

    default Optional<PersonRecord> findById(Integer recordId) {
        return selectOne(c -> c.where(PersonDynamicSqlSupport.id, SqlBuilder.isEqualTo(recordId)));
    }

    @Deprecated
    default List<PersonRecord> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, person, completer);
    }

    default List<PersonRecord> findAll() {
        return select(SelectDSLCompleter.allRows());
    }

    @Deprecated
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, person, completer);
    }

    default int updateByPrimaryKey(PersonRecord row) {
        return update(c -> c
                .set(firstName).equalToWhenPresent(row::getFirstName)
                .set(lastName).equalToWhenPresent(row::getLastName)
                .set(birthDate).equalToWhenPresent(row::getBirthDate)
                .set(employed).equalToWhenPresent(row::getEmployed)
                .set(occupation).equalToWhenPresent(row::getOccupation)
                .set(addressId).equalToWhenPresent(row::getAddressId)
                .where(id, SqlBuilder.isEqualTo(row::getId))
        );
    }

    @Deprecated
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, person, completer);
    }

    default int deleteByPrimaryKey(Integer recordId) {
        return delete(c -> c.where(id, SqlBuilder.isEqualTo(recordId)));
    }
}
