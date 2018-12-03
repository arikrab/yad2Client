package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Socket socket=null;
        InputStream inputStream=null;
        OutputStream outputStream=null;
        Scanner scanner=new Scanner(System.in);
        User user=new User();
        //byte[] test={88,99,43,77};
        //System.out.println(new String(test).toCharArray());
        try {
            socket=new Socket("127.0.0.1",3000);
            outputStream=socket.getOutputStream();
            inputStream=socket.getInputStream();
            byte[] byteBuffer=new byte[4];
            byte[] stringBuffer;
            int trueUser;

            System.out.println("what u want to do?");
            System.out.println("1 for login or 2 for creating a user");

            int option=scanner.nextInt();
            while (user.isActive()==false){
                int flag=0;
                    switch (option) {
                        case 1:{

                            //sendUser(User user,input stream,outputstream)
                            //sending the option to the server
                           ByteBuffer.wrap(byteBuffer).putInt(option);
                           outputStream.write(byteBuffer);

                           //sending user name to server

                            System.out.println("please enter user name:");
                            //setting the user name in client
                            user.setUserName(scanner.nextLine());
                            //send number of letters in the name to the server
                            ByteBuffer.wrap(byteBuffer).putInt(user.getUserName().length());
                            outputStream.write(byteBuffer);
                            //send the name to the server
                            stringBuffer=new byte[user.getUserName().length()];
                            stringBuffer=user.getUserName().getBytes();
                            outputStream.write(stringBuffer);

                            //putting password and send hashcode to the server
                            System.out.println("please enter user password:");

                            user.setPassword(scanner.nextLine());
                            ByteBuffer.wrap(byteBuffer).putInt(user.hashCode());
                            outputStream.write(byteBuffer);

                            //getting response if the username is correct
                            int actuallyRead=inputStream.read(byteBuffer);
                            if (actuallyRead!=4){
                                flag=1;
                                System.out.println("something went wrong");
                                return;
                            }
                            trueUser= ByteBuffer.wrap(byteBuffer).getInt();
                            if (trueUser==1){
                                user.setActive(true);
                            }
                            else{
                                System.out.println("the user does not exist.\n1.for login again.\n2.for create a user");
                                option=scanner.nextInt();
                            }



                        }
                            break;
                        case 2:{
                            //sendUser(User user,input stream,outputstream)
                            //sending the option to the server
                            ByteBuffer.wrap(byteBuffer).putInt(option);
                            outputStream.write(byteBuffer);

                            //sending user name to server
                            System.out.println("please enter user name:");
                            user.setUserName(scanner.nextLine());
                            stringBuffer=new byte[user.getUserName().length()];
                            stringBuffer=user.getUserName().getBytes();
                            outputStream.write(stringBuffer);

                            //putting password and send hashcode to the server
                            System.out.println("please enter user password:");
                            user.setPassword(scanner.nextLine());
                            ByteBuffer.wrap(byteBuffer).putInt(user.hashCode());
                            outputStream.write(byteBuffer);

                            //the server checking if the username is already in the pool

                            int actuallyRead=inputStream.read(byteBuffer);
                            if (actuallyRead!=4){
                                System.out.println("something went wrong");
                                return;
                            }
                            int validUser=ByteBuffer.wrap(byteBuffer).getInt();

                            if (validUser==1){
                                user.setActive(true);
                            }
                            if(validUser==0){
                                System.out.println("username is taken please choose another username");
                                option=2;
                            }

                        }
                            break;
                        default:
                            System.out.println("invalid choice please choose a valid option");
                            System.out.println("1.for login again.\n2.for create a user");
                            option=scanner.nextInt();

                    }
            }
            if (user.isActive()==true){
                System.out.println("you are connected what you want to do?");
                System.out.println("1.add item to the pool\n2.retrieve all correct items from the pool\n" +
                        "3.remove one of your items from the pool");
                System.out.println();
            }
            option=scanner.nextInt();

            while (user.isActive()==true){

                switch (option) {
                    case 1: {
                        break;
                    }
                    case 2: {
                        break;
                    }
                    case 3:{
                        break;
                    }
                    default:
                        break;
                }
            }


        }catch (IOException e){
            System.out.println("didnt find the host");
        }


    }
}
