package com.myself.project;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2020/11/21.
 */
public class UserManagerTest {


    @Test
    public void test(){
        List<OpsManagerModel> tree = OdpManagerDemo.getTree("100");
        System.out.println("tree = " + JSON.toJSONString(tree));
    }

    public static void main(String[] args) {
        OdpManagerDemo.test();
    }


    @Data
    static class OpsManagerModel{

        private String id;

        private String name;

        private String level;

        private String parent;

        private List<OpsManagerModel> children;

    }

    static class OdpManagerDemo{

        private static ArrayList<OpsManagerModel> odpList;

         static {
            ArrayList<OpsManagerModel> list = Lists.newArrayList();
            OpsManagerModel ops0 = new OpsManagerModel();
            ops0.setId("0");
            ops0.setName("zhang0");
            ops0.setLevel("A");
            ops0.setParent("100");
            list.add(ops0);
            OpsManagerModel ops1 = new OpsManagerModel();
            ops1.setId("1");
            ops1.setName("zhang1");
            ops1.setLevel("B");
            ops1.setParent("0");
            list.add(ops1);
            OpsManagerModel ops2 = new OpsManagerModel();
            ops2.setId("2");
            ops2.setName("zhang2");
            ops2.setLevel("C");
            ops2.setParent("0");
            list.add(ops2);
            OpsManagerModel ops3 = new OpsManagerModel();
            ops3.setId("3");
            ops3.setName("zhang3");
            ops3.setLevel("D");
            ops3.setParent("1");
            list.add(ops3);
            OpsManagerModel ops4 = new OpsManagerModel();
            ops4.setId("4");
            ops4.setName("zhang4");
            ops4.setLevel("E");
            ops4.setParent("1");
            list.add(ops4);
            OpsManagerModel ops5 = new OpsManagerModel();
            ops5.setId("5");
            ops5.setName("zhang5");
            ops5.setLevel("F");
            ops5.setParent("2");
            list.add(ops5);
            OpsManagerModel ops6 = new OpsManagerModel();
            ops6.setId("6");
            ops6.setName("zhang6");
            ops6.setLevel("G");
            ops6.setParent("2");
            list.add(ops6);
            OpsManagerModel ops7 = new OpsManagerModel();
            ops7.setId("7");
            ops7.setName("zhang7");
            ops7.setLevel("H");
            ops7.setParent("3");
            list.add(ops7);
            OpsManagerModel ops8 = new OpsManagerModel();
            ops8.setId("8");
            ops8.setName("zhang8");
            ops8.setLevel("I");
            ops8.setParent("3");
            list.add(ops8);
            OpsManagerModel ops9 = new OpsManagerModel();
            ops9.setId("9");
            ops9.setName("zhang9");
            ops9.setLevel("J");
            ops9.setParent("4");
            list.add(ops9);
            OpsManagerModel ops10 = new OpsManagerModel();
            ops10.setId("10");
            ops10.setName("zhang10");
            ops10.setLevel("K");
            ops10.setParent("4");
            list.add(ops10);
            OpsManagerModel ops11 = new OpsManagerModel();
            ops11.setId("11");
            ops11.setName("zhang11");
            ops11.setLevel("L");
            ops11.setParent("5");
            list.add(ops11);
            OpsManagerModel ops12 = new OpsManagerModel();
            ops12.setId("12");
            ops12.setName("zhang12");
            ops12.setLevel("M");
            ops12.setParent("5");
            list.add(ops12);
            OpsManagerModel ops13 = new OpsManagerModel();
            ops13.setId("13");
            ops13.setName("zhang13");
            ops13.setLevel("N");
            ops13.setParent("6");
            list.add(ops13);
            OpsManagerModel ops14 = new OpsManagerModel();
            ops14.setId("14");
            ops14.setName("zhang14");
            ops14.setLevel("O");
            ops14.setParent("6");
            list.add(ops14);
            odpList = list;
        }


        private static  List<OpsManagerModel> getOpsManagerforParent(String id){
            List<OpsManagerModel> opsManagerModels = new ArrayList<>();
            List<OpsManagerModel> managerModels = odpList;
            List<OpsManagerModel> collect = managerModels.stream().filter(manager -> StringUtils.equals(id, manager.getParent())).collect(Collectors.toList());
            opsManagerModels.addAll(collect);
            return opsManagerModels;
        }

        private static List<OpsManagerModel> getTree(String parentId){
            List<OpsManagerModel> tree = new ArrayList<>();
            List<OpsManagerModel> parent = getOpsManagerforParent(parentId);
            if(parent != null &&  parent.size() != 0){
                parent.forEach((opsManagerModel )->{
                    List<OpsManagerModel> models = getTree(opsManagerModel.getId());
                    opsManagerModel.setChildren(models);
                    tree.add(opsManagerModel);
                });
            }
            return tree;
        }

        private static void test(){
            System.out.println("Please Enter id:");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            List<OpsManagerModel> tree = OdpManagerDemo.getTree(name);
            System.out.println("tree = " + JSON.toJSONString(tree, SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat));
            test();
        }

    }

}
