package org.example;

public class NestedClassExample {
    private final String NON_STATIC_HI = "Hi!";
    private static final String STATIC_HI = "Hi!";
    private final NonStaticMemberClass nonStaticMemberClass = new NonStaticMemberClass();

    public static void main(String[] args) {
        NestedClassExample nestedClassExample = new NestedClassExample();

        // StaticMemberClass
        StaticMemberClass.sayHi();

        // NonStaticMemberClass
        nestedClassExample.nonStaticMemberClass.sayHi();
        // weird syntax to achieve the same as above
        nestedClassExample.new NonStaticMemberClass().sayHi();

        // AnonymousClass
        AnonymousClass anonymousClass = new AnonymousClass() {
            @Override
            public void sayHi() {
//                System.out.print(NON_STATIC_HI); // can't access non-static members
                System.out.print(STATIC_HI); // can access static members
                System.out.println(" I'm an AnonymousClass!");
            }
        };
        anonymousClass.sayHi();

        // Preferred method: lambda example to achieve the same as above
        AnonymousClass anonymousClass2 = () -> {
            System.out.print(STATIC_HI); // can access static members
            System.out.println(" I'm an AnonymousClass2!");
        };
        anonymousClass2.sayHi();

        // LocalClass
        nestedClassExample.sayHiFromLocalClass();
    }

    /**
     * Static member class
     *
     * Has access to all of the enclosing class' static members.
     *
     * Useful as public helper class. Use it if your requirements are similar to
     * those of a local class, you want to make the type more widely available,
     * and you don't require access to local variables or method parameters.
     */
    public static class StaticMemberClass {
        static void sayHi() {
//            System.out.print(NON_STATIC_HI); // can't access non-static members
            System.out.print(STATIC_HI); // can access static members
            System.out.println(" I'm a StaticMemberClass!");
        }
    }

    /**
     * Non-static member class
     *
     * Associated with an instance of the enclosing class.
     * Holds a reference to the enclosing instance which can lead to subtle memory leaks.
     *
     * Commonly used as an Adaptor to allow the enclosing class to be viewed as an
     * instance of a different class. Use a non-static nested class (or inner class)
     * if you require access to an enclosing instance's non-public fields and methods.
     * Use a static nested class if you don't require this access.
     */
    public class NonStaticMemberClass {
        void sayHi() {
            System.out.print(NON_STATIC_HI); // can access non-static membersc
//            System.out.print(STATIC_HI); // can access static members
            System.out.println(" I'm a NonStaticMemberClass!");
//            System.out.println(this);
        }
    }

    /**
     * Anonymous class interface
     *
     * Class instances implementing this interface are instantiated when it is first
     * declared. Anonymous classes don't have a defined class name. Is not a member of
     * it's enclosing class. Can't do instanceof or other tests that compare class names.
     *
     * Commonly used in the implementation of static factory methods. Use it if
     * you need to declare fields or additional methods.
     */
    interface AnonymousClass {
        void sayHi();
    }

    /**
     * Local class
     *
     * Can be declared basically anywhere a local variable can and obeys the same scoping rules.
     *
     * Least commonly used. Use it if you need to create more than one instance of a class,
     * access its constructor, or introduce a new, named type (because, for example,
     * you need to invoke additional methods later).
     */
    public void sayHiFromLocalClass() {
        class LocalClass {
            public void sayHi() {
                System.out.print(NON_STATIC_HI); // can access non-static members
//                System.out.print(STATIC_HI); // can access static members
                System.out.println(" I'm a LocalClass!");
            }
        }
        new LocalClass().sayHi();
    }

}
