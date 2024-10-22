
package KanBanAplication;

/**
 *
 * @author Retumets
 */



 /*
          ############## This is the Main  file, ##############
*/

public class RUN {
    public static void main(String[] args) {
        // Create and display the frame
       
        FrameUI frame = new FrameUI();
        frame.setVisible(true);
        
    }
}


 /*
............................

     ####### ORDER OF EXICUTION  ########

     #1. MAIN (This one)

     #2. RegistrationDiolog and Registration (stoors data catured in @UserDatabase class)
     
     #3. login (checks database if information enterd in login maches Database)

     #4. if login Sucsesfull launch Easy Kanban (@TaskManager class)

..............................
[in @TaskManager class]


     #5. choose Add Task, (outher options functonality coming soon...)

     #6. gets promted to enter  X amount of tasks, 

     #7. launces @AddTask class X amount of times. 
*/