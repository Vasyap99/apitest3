package p;

import java.util.Arrays.*;
import java.util.*;


import java.util.stream.*;


import java.io.*;

class apitest3{
    public static void main(String[]s){
        String t="koko pip 6";
        String[]ty=t.split(" ");

        /*for(int i=0;i<ty.length;i++)
            System.out.println(ty[i]);*/

        //создание списка из массива
        List al=Arrays.asList(ty);
        ArrayList a2=new ArrayList(al);

        Stream<String> stream = al.stream();
        Stream s2=stream.map( s1->s1.length() );
        System.out.println(s2.collect(Collectors.toList()));

        List al2=Arrays.stream( ty ).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(al2);

        //вывод списка
        //System.out.println(Arrays.toString(ty));  //!!не работает в jse1.4
        System.out.println(Arrays.asList(ty));  //использует toString() List

        //срез списка
        Object[]ol1=Arrays.copyOfRange(ty, 1, 2); //!!не работает в jse1.4
        System.out.println(Arrays.asList(ol1));
        List ol=Arrays.asList(ty).subList(0,2);
        System.out.println(ol);

        /////////////////
        /////////////////  читать файл по строкам, перекодировать и записать в файл
        FileInputStream fi=null;
        FileOutputStream fo=null;
        try{
            fi=new FileInputStream("p/t.txt");
            fo=new FileOutputStream("p/o.txt");
            Scanner sc=new Scanner(fi,"cp1251");
            while(sc.hasNextLine()){
                String ss=sc.nextLine();
                System.out.println(ss);
                fo.write(ss.getBytes("cp866"));
                fo.write(new byte[]{'\r','\n'});
            }
        }catch(Throwable e){
            e.printStackTrace();
        }finally{
            try{ 
                fi.close();
            }catch(Throwable e1){}
            try{ 
                fo.close();
            }catch(Throwable e2){}
        }

        ////////////////     чтение списка элементов каталога и преобразование через сборку  в список строк
                             //(тк listFiles() возвращает список файлов)
                             //+сортировка ч-з stream методом sorted
                             //+ join списка строк в строку
        File f=new File("../..");
        System.out.println(f.isDirectory());
        System.out.println(f.exists());
        List<File> list = Arrays.asList(f.listFiles());
        System.out.println( list ); 
        System.out.println( String.join( "\r\n", list.stream().map(File::getName).sorted((ss1,ss2)->(ss1.compareTo(ss2))).collect(Collectors.toList()) ) ); 


        ////////////////создание списка из фикс. элементов и расширение элементами другого списка
                            //(применение asList() требует преобразования в ArrayList)
        List nl=new ArrayList(  List.of("kok",-1,9.8)  );    
        System.out.println(nl);
        nl.addAll( Arrays.asList("qw er ty".split(" ")) );
        System.out.println(nl);
        Iterator i=nl.iterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }
        for(Object v:nl) System.out.println(v);
    }
}