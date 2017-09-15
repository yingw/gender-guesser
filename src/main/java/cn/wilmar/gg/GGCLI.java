package cn.wilmar.gg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Gender Generator CLI
 * 创建 by 殷国伟 于 2017/9/3.
 */
@Component
public class GGCLI implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(GGCLI.class);

    private static final String DEFAULT_COUNTRY = "CN";

    @Value("${gg.url}")
    String ggUrl;
    @Autowired
    RestTemplate restTemplate;


    @Override
    public void run(String... strings) throws Exception {
        String[] names = new String[]{"徐秋平", "杨艳林", "鲍文倩", "赵炯"
                //"常有强","徐海辉","杨力坤","朱亮","董宇","傅强","徐洁","朱雅娟","朱昊若","徐文静","操家振","何骏源","蔡城国","陈飞","陈红姣","陈啸丹","代丹丹","戴瑜","邓丽娟","丁晓涛","杜晨姗","冯真真","高宁宁","顾嘉茜","顾晶","顾友付","郭骁娟","洪金林","黄晓刚","周芳玲","周含菊","鲍铭","柴惠良","郝朝亮","何荣荣","贺军华","王浩然","梁冬蕾","梁晶晶","雷莉","邱子婴","吴长伟","赵卓","朱圣明","彭朋才","钱雯捷","聂晓勇","匡厚斌","姜哲洙","严峰","吴多清","杨广生","杨晓晶","岳胜超","邹联龙","赖彬","苏航","王豫山","伍利群","张逸洁","陆劲松","许峰杰","姚艳艳","叶俊杰","应博闻","王莉萍","张以宁","李占龙","赵文洁","王一凡","孙殿宇","林贻珀","漆彬","秦丹青","汪伟","王丽","薛娟娟","杨国民","杨合星","李恺昱","凌志玮","卫瑞欣","左金","吴潇","朱捷","曹萌","林莉","张延","张丽莉","王玲","蒋殷","廖启雯","张励文","胡章艮","夏琴艳","张文汉","郑继臣","卢峻","王艳","谢晶","俞春华","张燕","陈慧","余欢","李大伟","张妤","黄文翔","仝鑫","吴婕","尤佳","唐君茂","钱玮","王飞","杨锐锋","杨迎伟","张朝初","梁健","唐瓛","张天","王静茹","刘冬平","程婷婷","吴璟","李纬","秦云","陶三平","左刚举 ","吴晓敏","陈巍麟","史晓彦","边斯楠","陈琨","艾方园","沙云蕾","王玉祥","殷国伟","邵佳","马玉梅","王劲松","张健","齐慧芳","乐军","刘哲良","葛少芳","卢阳阳","虞炜","艾真保","邵远慧","潘鑫冬","叶德锟","凌志伟","刘光道","王闯","欧晓君","姜晓晴","廖玲","韩林","姚献","王晔","张文骏","张俊","张漪婷","袁美英","曹盛清","金亚东","顾波","陈竹宇","舒启鑫","陈威","王冲","张金伟","鲍克双","吕青海","余薇","吴烨","李勇","张昶","邓阳春","吕杰敏","刘晓","姜坤","杨程","陈旋","袁巧凤","刘卉","杨灿","章晖","徐雷","毛望宸","杨乐波","储峻","辜纪兴","周禹","何思洁","龙望","王小妹","沈文艳","蒲云江","覃铁云","詹亮亮","王天宇","周雨","殷魁","孟祥骞","杨明瑞","张程程","刘云","牛亮","曾庆静","庞士佩","刘坚","徐晓红","徐杰","张源","王笑","陆陈嫣","甘征征"
        };
        System.out.println(ggUrl);
        Arrays.stream(names).forEach(name -> getGender(name.substring(1)));
    }

    private Gender getGender(String name) {
        return getGender(DEFAULT_COUNTRY, name);
    }

    private Gender getGender(String country, String name) {
        if (StringUtils.isEmpty(name)) throw new RuntimeException("名字不能空！");
        GGResult gender = restTemplate.getForEntity(ggUrl.replace("##name##", name).replace("##country##", country), GGResult.class).getBody();
        System.out.println("return gender = " + gender);
        switch (gender.getGender()) {
            case "unknown":
                return Gender.UNKNOWN;
            case "male":
                return Gender.MALE;
            case "female":
                return Gender.FEMALE;
        }
        throw new RuntimeException("GG 返回了未知错误！ gender: " + gender);
    }
}
