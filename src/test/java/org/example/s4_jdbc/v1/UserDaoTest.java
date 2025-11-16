package org.example.s4_jdbc.v1;

import org.junit.jupiter.api.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("UserDao JDBC 통합 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserDaoTest {

    private static final User TEST_USER = new User("wizard", "password", "name", "email");

    private UserDao userDao;

    @BeforeEach
    void setUp() {
        // DB 스키마 및 초기 데이터 세팅
        var populator = new ResourceDatabasePopulator(new ClassPathResource("db_schema.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());

        userDao = new UserDao();
    }

    @Test
    @DisplayName("사용자를 저장하면 같은 ID로 다시 조회할 수 있다")
    void create_and_find_user_by_id() throws SQLException {
        // given
        userDao.create(TEST_USER);

        // when
        User found = userDao.findByUserId(TEST_USER.getUserId());

        // then
        assertThat(found)
                .usingRecursiveComparison()
                .isEqualTo(TEST_USER);
    }

    @Test
    @DisplayName("존재하지 않는 ID로 조회하면 null 을 반환한다")
    void findByUserId_when_user_does_not_exist_returns_null() throws SQLException {
        // given
        userDao.create(TEST_USER);

        // when
        User found = userDao.findByUserId("hong");

        // then
        assertThat(found).isNull();   // findByUserId 가 Optional 을 리턴한다면 isEmpty() 등으로 변경
    }
}
