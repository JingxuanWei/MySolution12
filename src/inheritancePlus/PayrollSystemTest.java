package inheritancePlus;// Fig. 10.9: PayrollSystemTest.java
// Employee hierarchy test program.

public class PayrollSystemTest {
	public static void main( String[] args )
     {
        //INHERITANCE DEMO DRIVER CODE =================================================================================
        // create subclass objects                                          
        SalariedEmployee salariedEmployee = new SalariedEmployee( "John", "Smith", "111-11-1111", 800.00);
        HourlyEmployee hourlyEmployee = new HourlyEmployee( "Karen", "Price", "222-22-2222", 16.75, 40);
        CommissionEmployee commissionEmployee = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06);                     
        BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300);

        System.out.println( "Employees processed individually:\n" );

        //objects are pointed at by a reference of the same class, the implicit toString of each subclass executes because of overriding
        System.out.printf( "%s\n%s: $%,.2f\n\n", salariedEmployee, "earned", salariedEmployee.earnings());
        System.out.printf( "%s\n%s: $%,.2f\n\n", hourlyEmployee, "earned", hourlyEmployee.earnings());
        System.out.printf( "%s\n%s: $%,.2f\n\n", commissionEmployee, "earned", commissionEmployee.earnings());
        System.out.printf( "%s\n%s: $%,.2f\n\n", basePlusCommissionEmployee, "earned", basePlusCommissionEmployee.earnings());

        //POLYMORPHISM USING INHERITANCE INCLUDING A DOWNCAST DEMO DRIVER CODE =========================================
        // create four-element Employee array, objects are pointed at by references of the ancestor Employee class
        Employee[] employees = new Employee[ 4 ]; //an array of Employee pointers pointing at objects of subclasses of Employee

        // initialize array with Employees          
        employees[ 0 ] = salariedEmployee;          
        employees[ 1 ] = hourlyEmployee;            
        employees[ 2 ] = commissionEmployee;        
        employees[ 3 ] = basePlusCommissionEmployee;


        System.out.println( "Employees processed polymorphically:\n" );
        
        
//        for (int i = 0; i < employees.length; i++){
//        	System.out.println( employees[i] );
//        	System.out.printf("earned $%,.2f\n\n", employees[i].earnings() );
//        }
        
        // polymorphically process each element in array employees
        for ( Employee currentEmployee : employees ){
           System.out.println( currentEmployee ); // invokes implicit toString - Q. Which toString()? A. the toString() appropriate to the subclass of the object pointed at by currentEmployee

           // no special treatment for BasePlusCommissionEmployee required - we are just showing we can do it if required using instanceOff
           if ( currentEmployee instanceof BasePlusCommissionEmployee ){

              // downcast Employee reference to BasePlusCommissionEmployee reference because Employee does not recognise get/set BaseSalary neither should it
              BasePlusCommissionEmployee bcEmployee = ( BasePlusCommissionEmployee ) currentEmployee;                //only works at runtime if currentEmployee is pointing at BasePlusCommisionEmployee which we have ensured
              bcEmployee.setBaseSalary( 1.10 * bcEmployee.getBaseSalary() );

              //alternative which does not require a downcast reference variable
//              ((BasePlusCommissionEmployee)currentEmployee).
//                      setBaseSalary(1.10 * ((BasePlusCommissionEmployee)currentEmployee).getBaseSalary());

              System.out.printf("new base salary with 10%% increase is: $%,.2f\n", bcEmployee.getBaseSalary() );
           }

           System.out.printf("earned $%,.2f\n\n", currentEmployee.earnings() ); //Q. Which earnings()? A. the earnings() appropriate to the subclass of the object pointed at by currentEmployee
        }

        // get type name of each object's class in the employees array
        for ( int j = 0; j < employees.length; j++ )      
           System.out.printf( "Employee %d is a %s\n", j, employees[ j ].getClass().getName() );
     }
}

