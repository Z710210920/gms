package com.Zyuchen.ValicateCodeservice.controller;

import com.Zyuchen.ValicateCodeservice.service.ValicateCodeService;
import com.Zyuchen.ValicateCodeservice.utils.VerifyCode;
import com.Zyuchen.common.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/ValicateCode")
@CrossOrigin //跨域
public class ValicateCodeApiController {

    @Autowired
    private ValicateCodeService valicateCodeService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /*@GetMapping(value = "/send/{phone}")
    public R code(@PathVariable String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) return R.ok();

        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code", code);
        boolean isSend = valicateCodeService.send(phone, "SMS_180051135", param);
        if(isSend) {
            redisTemplate.opsForValue().set(phone, code,5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("发送短信失败");
        }
    }*/

    @ApiOperation(value = "验证码")
    @GetMapping("/getVerifyCode")
    public R verifyCode() {
        try {
            VerifyCode verifyCode = valicateCodeService.generate(80, 28);
            String code = verifyCode.getCode();
            System.out.println(code);
            System.out.println(verifyCode.getUUID());
            redisTemplate.opsForValue().set(verifyCode.getUUID(), code,5, TimeUnit.MINUTES);
            return R.ok().data("varifyCode", verifyCode.getImgBytes())
                    .data("uuid", verifyCode.getUUID());
        } catch (IOException e) {
            e.printStackTrace();
            return R.error().message(e.getMessage());
        }
    }

    @ApiOperation(value = "验证验证码")
    @GetMapping("/Verify")
    public R verify(@PathVariable VerifyCode verifyCode) {
            String code = verifyCode.getCode();
            String UUID = verifyCode.getUUID();
            String trueCode = redisTemplate.opsForValue().get(UUID);
            if(trueCode == null){
                return R.error().message("验证码失效,请重新获取");
            }else if(trueCode.equals(code)){
                return R.ok();
            }else{
                return R.error().message("验证码错误");
            }
    }
}
