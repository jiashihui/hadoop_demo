package util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class HDFSOperation {

    // 将本地文件上传到HDFS
    public static void CopyFromLocal(Configuration conf) throws Exception {
        conf.set("fs.defaultFS", "hdfs://192.168.1.111:9001");
        FileSystem fs = FileSystem.get(conf);
        Path src = new Path("src/input/test.txt");
        Path dst = new Path("hdfs://192.168.1.111:9001/home/");
        fs.copyFromLocalFile(src, dst);
        System.out.println("upload to " + conf.get("fs.default.name"));
        fs.close();
    }

    // 将HDFS上的文件传回到本地
    public static void CopyToLocal(Configuration conf) throws Exception {
        conf.set("fs.defaultFS", "hdfs://192.168.1.111:9001");
        FileSystem fs = FileSystem.get(conf);
        Path src = new Path("hdfs://192.168.1.111:9001/home/test.txt");
        Path dst = new Path("src/output/");
        fs.copyToLocalFile(src, dst);
        fs.close();
    }

    // 在HDFS上创建文件
    public static void CreateFile () throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9001");
        FileSystem fs = FileSystem.get(conf);
        Path dfs = new Path("hdfs://localhost:9001/home/test.txt");
        FSDataOutputStream outputStream = fs.create(dfs);
        byte[] buff = "hello world!".getBytes();
        outputStream.write(buff,0, buff.length);
        outputStream.close();
        fs.close();
    }

    // 删除文件/目录, 当删除对象为目录时，将第二个参数设为true否则将产生异常
    public static void DeleteFile () throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9001");
        FileSystem fs = FileSystem.get(conf);
        Path dfs = new Path("hdfs://localhost:9001/home/test.txt");
        System.out.println(fs.delete(dfs,true));
        fs.close();
    }

    // 创建目录
    public static void MakeDir () throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9001");
        FileSystem fs = FileSystem.get(conf);
        Path dfs = new Path("hdfs://localhost:9001/home1/");
        System.out.println(fs.mkdirs(dfs));
        fs.close();
    }

    // 读取文件
    public static void ReadFile () throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9001");
        FileSystem fs = FileSystem.get(conf);
        Path dst = new Path("hdfs://localhost:9001/home/test.txt");
        if(fs.exists(dst) && !fs.isDirectory(dst)) {
            FSDataInputStream is = fs.open(dst);
            FileStatus stat = fs.getFileStatus(dst);
            byte[] buffer = new byte[(int) stat.getLen()];
            is.read(buffer);
            System.out.println(new String(buffer));
            is.close();
            fs.close();
        } else {
            throw new Exception("fail to read file "+dst.toString());
        }
    }

    // 追加文件内容，注:需要将hdfs-site.xml中的dfs.support.append属性设置为true
    public static void AppendFile () throws Exception {
        Configuration conf = new Configuration();
        conf.addResource(new Path("/usr/local/hadoop-2.7.2/etc/hadoop/core-site.xml"));
        FileSystem fs = FileSystem.get(conf);
        Path dfs = new Path("hdfs://localhost:9001/home/test.txt");
        FSDataOutputStream outputStream = fs.append(dfs);
        byte[] buff = "test".getBytes();
        outputStream.write(buff);
        outputStream.close();
        fs.close();
    }

}
