package test;

import benchmark.internal.BenchmarkN;
import benchmark.objects.A;
import benchmark.objects.B;

/*
 * @testcase FieldSensitivity2
 *
 * @version 1.0
 *
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 *
 * @description Field Sensitivity without static method
 */
public class MemberVar {

  A x;

  public MemberVar() {
    x=new A();
  }

  private void assign(A x, A y) {
    y.f = x.f;
  }

  private void test() {
    BenchmarkN.alloc(1);
    B b = new B();
    BenchmarkN.alloc(2);
    A a = new A(b);
    BenchmarkN.alloc(3);
    A c = new A();
    BenchmarkN.alloc(4);
    B e = new B();
    assign(a, c);
    B d = c.f;
    x.f=d;
    BenchmarkN.test(1, d);
  }

  public void test2() {
    BenchmarkN.alloc(5);
    B b = new B();
    BenchmarkN.alloc(6);
    B c = new B();
    BenchmarkN.alloc(7);
    A a1 = new A();
    BenchmarkN.alloc(8);
    A a2 = new A();
    a1.f = b;
    a2.f = c;
    BenchmarkN.test(2, a1.f);
    BenchmarkN.test(3, a2.f);
  }


  public static void main(String[] args) {

    BenchmarkN.alloc(9);
    MemberVar fs2 = new MemberVar();
    fs2.test();
    fs2.test2();

  }

}
