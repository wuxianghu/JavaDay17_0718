package test01;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.junit.Test;

/**
 * @author azzhu
 * @create 2020-07-18 13:29:50
 */
public class TestHutoolOther {

    /**
     * 验证码的使用
     */
    @Test
    public void testCaptcha() {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

//图形验证码写出，可以写出到文件，也可以写出到流
       // lineCaptcha.write("d:/line.png");
//输出code
        Console.log(lineCaptcha.getCode());
//验证图形验证码的有效性，返回boolean值
        System.out.println(lineCaptcha.verify("1234"));

//重新生成验证码
      //  lineCaptcha.createCode();
      //  lineCaptcha.write("d:/line.png");
//新的验证码
      //  Console.log(lineCaptcha.getCode());
//验证图形验证码的有效性，返回boolean值
     //   lineCaptcha.verify("1234");
    }

    @Test
    public void testCode2() {
        // 自定义纯数字的验证码（随机4位数字，可重复）
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        lineCaptcha.setGenerator(randomGenerator);
// 重新生成code
        lineCaptcha.createCode();
        System.out.println("=====>"+lineCaptcha.getCode());
    }

    @Test
    public void testStr() {
        if (StrUtil.isNotEmpty("aaa")) {
            System.out.println("aaa");
        }
    }

    @Test
    public void testEncode() {
        System.out.println(DigestUtil.md5Hex("123456"));

    }
}
