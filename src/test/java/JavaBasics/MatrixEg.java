package JavaBasics;

import org.testng.annotations.Test;

public class MatrixEg {

    @Test
    public void Eg1() {

        /*  2 4 5
            3 4 7
            1 2 9
*/

        int[][] array = {{2, 4, 5}, {3, 0, 7}, {1, 2, 9}};
        int num = array[0][0];

        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array.length; j++) {

                if (array[i][j] < num) {
                    num = array[i][j];
                }

            }


        }

        System.out.println("Smallest Number :" + num);
    }


    @Test
    public void Eg2() {

        /*  2 4 5
            3 4 0
            1 2 9
*/

        int[][] array = {{2, 4, 5}, {3, 4, 0}, {1, 2, 9}};
        int num = array[0][0];
        int colmn = 0;

        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length;j++){
                if(array[i][j]<num){
                    num=array[i][j];
                   colmn=j;
                }
            }
        }
        System.out.println(colmn);

        int k = 0;
        int expectedNumber =array[k][colmn];

        while(k<3){
            if(array[k][colmn]>expectedNumber){
                expectedNumber =array[k][colmn];

            }
            k++;

        }

        System.out.println("Largest Number in Smallest Column : "+expectedNumber);





    }
}
