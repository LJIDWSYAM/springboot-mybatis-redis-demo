package com.wjl.springbootmybatis.Utils;

/* *
 *类名：AlipayConfig
 *作者：Rachel
 */

public class AlipayConfig {

    //应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092800618166";

    //商户私钥，您的PKCS8格式RSA2私钥   私钥
    public static String private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCv14nxzIIB2rcUS8Uupe1pE82UO4F+Tp6tc0FSk6rckmTXFj/I1XI0cHbC/U9qZIVMjUB+GxoBE11VcM/s9hoCgrwCgasZrO07Gp+usVlfTB6fsS4hvWPrF7GPIXsLJoavTPWzSoMoRsvl0B+wfE7kNc4zt3yJdKhRh+z83AwSCu6hK1SMRpCAlLrgygfXFyXSxlRjcXZXV33yp3gFH3EK+d1fNxqCziwUQDnxTN8+0kaAxx45KH2tsPaS0LF3LubF+rKUvWaAGT8DlWccQr1alU0ASjUdS+hWvoctxs2PI/FHGhwWHCh1oWWdHLC4MXIKkRLfnbgz6cWKb7qGcmMrAgMBAAECggEBAKrwn5s+MgxXcK78Ub3ddAQ4ShuWFXaSO1NmvdPm1a0Da6BkcrFsPk+YHPvFgNRuz97xWga9lfQBvTP4LUmwnOxf5yGQqKKXJy/T2/PZWIZ52zNm7O/Dy0SZjfUUmSbn1vrHl/sproMS/9hzELtivNbBL6m68Ag4LgDEYLAXwmwuM0x2I8e47b7ASsYhtaxEOwaECdH7pVs+qA8hDUz48umYiIoTwAzSiohqnIYYr0BsR0FYKg3Ki2QGjzV3KB/s1FVLtYS35/c++2GjbULxVpw8Ccb5gc1arIEDQyQLNNjkB+Fz60jdLIaNkp5hbt9hAa1P0kogzMCrXN3FI4VoQUECgYEA4IH94Yobstl2n+BLF7nxa6yKDm52Blz9N6pJHYPk/Va3Vz2F9T+ZgPGqi0J+FqkojUNtCPZXSlSnculmB68O6Xk8p2SEGjSaCVpcnIrdzoMeM36sKIAAgbW8RCg8BHK9XpuXzj6IX3B3GrHzNEmmTtQJ55lhvoz3ke9rzFnZDXECgYEAyIH4sHB6/8K4+BcNWBCik2Dx+arM2E2YAUp6SVCXR/OjIx7anOoWafrjZAWOVYfmA5okhdPGkEtwk4y3luROCX2SSkng3NEx56IKxWpFRXOqmt9ZVbXPzSil/LCSXD/QSfNUzIZmTTRIRZUl6bEPt9RCv6SRwqk8a3Q9WGnWXFsCgYEAiAa66JhhR+/yq9OeK/YBjJex3iSXZiuZUMzgF/3+FHEiDL2+GGY8f5/tEQtskhZT96NOwQtgwCyieEFqDGXIxzUpxtJkJ+yPF/OkAUUyNxnChrjNXoyPwgd21mT6WEec7WGgjNnejtT5lTcUgC3PkhtvFOAeFPIcXlOOIPafSEECgYAwCVtgQgJlxev0m+k5gPrCHka6jt32sFhLbuFvMRydQ1SBNIIhd7+Y8l+J9f7MpPtJP6OiYENBDKHS57KQNvAvO+NRYvO5U3EGUn8MHtKrufNUmU/Yxe8b8rIZoN/fs/Yd0WsueMCHnI/4A6yKmT3vx62aJj45ibwM7lNIHEsb4wKBgGjeWQlpdvZqP0S06i8cK7+kglZrgXdmzKsHX8plYQaHczh3R6hA10U2XtLbSzMSUkwS77VKUVNgEwuvLHwqjh97/HVe9Ub97wyynE5Z7H340Wth7qzyPGpgCPNRRZyQmE1jkLAGMzU+oh9CDpcxkBOm0lZKVJRke8G858A3vU+u";

    //Controller Mapping:得放到服务器上，且使用域名解析 IP
    public static String notify_url = "/notifyUrl";

    //Controller Mapping:得放到服务器上，且使用域名解析 IP
    public static String return_url = "/returnUrl";

    // 支付宝网关（注意沙箱alipaydev，正式则为 alipay）
    public static String url = "https://openapi.alipaydev.com/gateway.do";

    public static String charset = "UTF-8";

    public static String format = "json";

    //RSA2 支付宝公钥
    public static String public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1ZbhNWC/dPBZEBAkVnP7yhfFRT2FBKFArms3P+Nfl33VTSQ3xm08d/GOPzls7+HQASv6Bz1UKmONKyLmUvrYcSiJWbN1qt19Ai92U0YirF6Kj1C+8U3sxKFfjHUydEOR7cZXSiATnfIQPk4xW18CraB2rkf7L58EA30zDItGOB5G4mYg1XoF3K5xqiQ5MonqbN5nBVX1QTY5x5bEhIebUI4MYWGvz+bJo3ZDUGuuzPgq4bc9SwQCO5VAivBX33snq4ZstO4EGMj7SXevuMHw5TARgID6b/vGvJkpfhTa+yLgvkjcUp2lp21ltS9fCzhlOcK6VXkaoW4Ns30Hd944GQIDAQAB";

    public static String signtype = "RSA2";


}