package proto;

import cn.pojo.Basic;
import cn.pojo.Enum;
import cn.pojo.Importing;
import cn.pojo.Nest;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

import java.util.Arrays;

/**
 * 使用protobuf:compile命令创建Java文件
 *
 * @user 郑超
 * @date 2021/4/25
 */
public class TestProto {

    @Test
    public void testBasic() throws InvalidProtocolBufferException {
        Basic.BasicProto basic = Basic.BasicProto.newBuilder()
                .setMoney(4999.99).setHeight(17.74F).setAge(24)
                .setDistance(1000).setSex(false).setNameBytes(ByteString.copyFromUtf8("华为荣耀")).build();
        byte[] bytes = basic.toByteArray();
        System.out.println(Arrays.toString(bytes));
        System.out.println(bytes.length);

        Basic.BasicProto parse = Basic.BasicProto.parseFrom(bytes);
        System.out.println(parse);
        System.out.println(parse.getName());
    }

    @Test
    public void testEnum() throws InvalidProtocolBufferException {
        Enum.EnumProto enumProto = Enum.EnumProto.newBuilder()
                .setSeasons(Enum.EnumProto.Seasons.SUMMER).setWeather(Enum.EnumProto.Weather.RAIN).build();
        byte[] bytes = enumProto.toByteArray();
        System.out.println(Arrays.toString(bytes));
        System.out.println(bytes.length);

        Enum.EnumProto parse = Enum.EnumProto.parseFrom(bytes);
        System.out.println(parse);
        System.out.println(parse.getWeather());
        System.out.println(parse.getSeasons());
    }

    @Test
    public void testImport() throws InvalidProtocolBufferException {
        Basic.BasicProto basic = Basic.BasicProto.newBuilder()
                .setMoney(4999.99).setHeight(17.74F).setAge(24)
                .setDistance(1000).setSex(false).setNameBytes(ByteString.copyFromUtf8("华为荣耀")).build();
        Enum.EnumProto enumProto = Enum.EnumProto.newBuilder()
                .setSeasons(Enum.EnumProto.Seasons.SUMMER).setWeather(Enum.EnumProto.Weather.RAIN).build();

        Importing.ImportProto importProto = Importing.ImportProto.newBuilder()
                .setEnumProto(enumProto).setBasic(basic).build();
        byte[] bytes = importProto.toByteArray();
        System.out.println(Arrays.toString(bytes));
        System.out.println(bytes.length);

        Importing.ImportProto parse = Importing.ImportProto.parseFrom(bytes);
        System.out.println(parse);
    }

    @Test
    public void testNest() throws InvalidProtocolBufferException {
        Nest.NestProto.Animal.Dog dog = Nest.NestProto.Animal.Dog.newBuilder()
                .setAge(6).setName("小狗").build();
        Nest.NestProto.Dinosaur dinosaur = Nest.NestProto.Dinosaur.newBuilder()
                .setWeight(8000.56).setName("大龙").build();
        Nest.NestProto nest = Nest.NestProto.newBuilder()
                .setDinosaur(dinosaur).setDog(dog).build();
        byte[] bytes = nest.toByteArray();
        System.out.println(Arrays.toString(bytes));
        System.out.println(bytes.length);

        Nest.NestProto parse = Nest.NestProto.parseFrom(bytes);
        System.out.println(parse);
    }

}
