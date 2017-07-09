package inheritancePlus;


/**
 * Created by shuxford on 25/03/2016.
 */
public class DoingIt {
    Window myWindow;

    public void doIt(Window win){
        myWindow = win;

        payrollSystemDriverTest();
    }

    private void payrollSystemDriverTest(){
        //INHERITANCE DEMO DRIVER CODE =================================================================================
        // create subclass objects
        SalariedEmployee salariedEmployee = new SalariedEmployee( "John", "Smith", "111-11-1111", 800.00);
        HourlyEmployee hourlyEmployee = new HourlyEmployee( "Karen", "Price", "222-22-2222", 16.75, 40);
        CommissionEmployee commissionEmployee = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06);
        BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300);

        myWindow.clearOut();
        myWindow.writeOutLine( "Employees processed individually:\n" );

        //objects are pointed at by a reference of the same class, the implicit toString of each subclass executes because of overriding
        myWindow.writeOutLine(String.format( "%s\n%s: $%,.2f\n\n", salariedEmployee, "earned", salariedEmployee.earnings()));
        myWindow.writeOutLine(String.format( "%s\n%s: $%,.2f\n\n", hourlyEmployee, "earned", hourlyEmployee.earnings()));
        myWindow.writeOutLine(String.format( "%s\n%s: $%,.2f\n\n", commissionEmployee, "earned", commissionEmployee.earnings()));
        myWindow.writeOutLine(String.format( "%s\n%s: $%,.2f\n\n", basePlusCommissionEmployee, "earned", basePlusCommissionEmployee.earnings()));



        //POLYMORPHISM USING INHERITANCE INCLUDING A DOWNCAST DEMO DRIVER CODE =========================================
        // create four-element Employee array, objects are pointed at by references of the ancestor Employee class
        Employee[] employees = new Employee[ 4 ]; //an array of Employee pointers pointing at objects of subclasses of Employee

        // initialize array with Employees
        employees[ 0 ] = salariedEmployee;
        employees[ 1 ] = hourlyEmployee;
        employees[ 2 ] = commissionEmployee;
        employees[ 3 ] = basePlusCommissionEmployee;

        myWindow.writeOutLine( "Employees processed polymorphically:\n" );


//        for (int i = 0; i < employees.length; i++){
//        	System.out.println( employees[i] );
//        	System.out.printf("earned $%,.2f\n\n", employees[i].earnings() );
//        }

        // polymorphically process each element in array employees
        for ( Employee currentEmployee : employees ){
            myWindow.writeOutLine( currentEmployee ); // invokes implicit toString - Q. Which toString()? A. the toString() appropriate to the subclass of the object pointed at by currentEmployee

            // no special treatment for BasePlusCommissionEmployee required - we are just showing we can do it if required using instanceOff
            if ( currentEmployee instanceof BasePlusCommissionEmployee ){

                // downcast Employee reference to BasePlusCommissionEmployee reference because Employee does not recognise get/set BaseSalary neither should it
                BasePlusCommissionEmployee bcEmployee = ( BasePlusCommissionEmployee ) currentEmployee; //only works at runtime if currentEmployee is pointing at BasePlusCommisionEmployee which we have ensured

                bcEmployee.setBaseSalary( 1.10 * bcEmployee.getBaseSalary() );

                //((BasePlusCommissionEmployee)currentEmployee).getBaseSalary(); //same as previous line but no wasted down cast variable

                myWindow.writeOutLine(String.format("new base salary with 10%% increase is: $%,.2f\n", bcEmployee.getBaseSalary()));
            }

            myWindow.writeOutLine(String.format("earned $%,.2f\n\n", currentEmployee.earnings() )); //Q. Which earnings()? A. the earnings() appropriate to the subclass of the object pointed at by currentEmployee
        }

        // get type name of each object's class in the employees array
        for ( int j = 0; j < employees.length; j++ )
            myWindow.writeOutLine(String.format( "Employee %d is a %s\n", j, employees[ j ].getClass().getName()));
    }

}
