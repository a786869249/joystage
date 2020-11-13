package cn.core.common.utils;

import cn.core.common.entity.JsonResult;
import cn.core.common.enums.ResultCode;

/**
 * @author shanjianfei
 * @create 2020-10-15 19:26
 */
public class ResultTool {
    public static JsonResult success() {
        return new JsonResult(true);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult(true, data);
    }

    public static JsonResult fail() {
        return new JsonResult(false);
    }

    public static JsonResult fail(ResultCode resultEnum) {
        return new JsonResult(false, resultEnum);
    }
}
