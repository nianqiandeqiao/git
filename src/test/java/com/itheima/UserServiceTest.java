package com.itheima;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@DisplayName("UserService 单元测试类")
public class UserServiceTest {
    private UserService userService;

    @BeforeEach
    public void setup() {
        userService = new UserService();
    }

    // ==================== getAge 测试 ====================

    @Test
    @DisplayName("getAge: 正常身份证号, 返回正确年龄")
    public void testGetAge_normal() {
        // 使用一个固定的身份证号来验证年龄计算逻辑
        String idCard = "110101199001011234";
        String birthday = idCard.substring(6, 14);
        LocalDate birthDate = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyyMMdd"));
        int expectedAge = Period.between(birthDate, LocalDate.now()).getYears();

        Integer age = userService.getAge(idCard);
        Assertions.assertEquals(expectedAge, age, "年龄计算不正确");
    }

    @Test
    @DisplayName("getAge: 2008年出生的身份证号, 年龄大于0")
    public void testGetAge_2008() {
        Integer age = userService.getAge("123432200803245416");
        Assertions.assertNotNull(age);
        Assertions.assertTrue(age > 0, "年龄应大于0");
    }

    @Test
    @DisplayName("getAge: 传入null, 抛出IllegalArgumentException")
    public void testGetAge_null() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.getAge(null),
                "传入null应抛出IllegalArgumentException");
    }

    @Test
    @DisplayName("getAge: 传入长度不足18位的身份证号, 抛出IllegalArgumentException")
    public void testGetAge_invalidLength() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.getAge("123456"),
                "长度不足18位应抛出IllegalArgumentException");
    }

    @Test
    @DisplayName("getAge: 传入超过18位的身份证号, 抛出IllegalArgumentException")
    public void testGetAge_tooLong() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.getAge("1234567890123456789"),
                "超过18位应抛出IllegalArgumentException");
    }

    // ==================== getGender 测试 ====================

    @Test
    @DisplayName("getGender: 第17位为奇数, 返回'男'")
    public void testGetGender_male() {
        // 身份证号第17位是1(奇数) -> 男
        String gender = userService.getGender("123432200803245416");
        Assertions.assertEquals("男", gender, "第17位为奇数时应返回'男'");
    }

    @Test
    @DisplayName("getGender: 第17位为偶数, 返回'女'")
    public void testGetGender_female() {
        // 身份证号第17位是6(偶数) -> 女
        String gender = userService.getGender("123432200803245466");
        Assertions.assertEquals("女", gender, "第17位为偶数时应返回'女'");
    }

    @Test
    @DisplayName("getGender: 传入null, 抛出IllegalArgumentException")
    public void testGetGender_null() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.getGender(null),
                "传入null应抛出IllegalArgumentException");
    }

    @Test
    @DisplayName("getGender: 传入长度不足18位的身份证号, 抛出IllegalArgumentException")
    public void testGetGender_invalidLength() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.getGender("12345"),
                "长度不足18位应抛出IllegalArgumentException");
    }

    @DisplayName("getGender: 参数化测试 - 多个奇数位身份证号均返回'男'")
    @ParameterizedTest
    @ValueSource(strings = {"123432200803245416", "123432200803245436", "123432200803245456"})
    public void testGetGender_parameterizedMale(String idCard) {
        String gender = userService.getGender(idCard);
        Assertions.assertEquals("男", gender);
    }

    @DisplayName("getGender: 参数化测试 - 多组身份证号与对应性别匹配")
    @ParameterizedTest
    @CsvSource({
            "123432200803245416, 男",  // 第17位=1(奇数)
            "123432200803245426, 女",  // 第17位=2(偶数)
            "123432200803245436, 男",  // 第17位=3(奇数)
            "123432200803245446, 女",  // 第17位=4(偶数)
            "123432200803245456, 男",  // 第17位=5(奇数)
            "123432200803245466, 女"   // 第17位=6(偶数)
    })
    public void testGetGender_parameterizedBoth(String idCard, String expectedGender) {
        String gender = userService.getGender(idCard);
        Assertions.assertEquals(expectedGender, gender,
                "身份证号 " + idCard + " 的性别应为 " + expectedGender);
    }
}
