package pk1;

import jdk.dynalink.linker.LinkerServices;

import java.util.*;
import java.util.function.Predicate;

public class filteringappels {
    public static class Appel{
        public enum Color{
            RED, GREEN;
        }
        private int weight;
        private Color color;
        public Appel(int weight, Color color){
            this.weight=weight;
            this.color=color;

        }
        public Color getColor(){
            return color;
        }
        public void setColor(Color color){
            this.color=color;
        }
        public int getWeight() {
            return weight;
        }
        public void setWeight(int weight){
            this.weight=weight;
        }
        @Override
        public String toString() {
            return "Appel{" +
                    "weight=" + weight +
                    ", color=" + color +
                    '}';
        }
        //interface focntionelle elle contient une seule methode
        //methode test qui return un boolean qui verifie une condition
        public interface ApplePredicate extends Predicate<Appel> {

        }
        public static class AppleWeightPredicate implements ApplePredicate{

            @Override
            public boolean test(Appel apple) {
                return apple.getWeight()>=150;
            }
        }
        public static class AppleColorPredicate implements ApplePredicate{

            @Override
            public boolean test(Appel apple) {
                return apple.getColor()== Color.GREEN;
            }
        }
        public static class AppleRedAndHeavyPredicate implements ApplePredicate{

            @Override
            public boolean test(Appel apple) {
                return apple.getColor()== Color.RED && apple.getWeight()>=150;
            }
        }
        public static List<Appel> filter(ApplePredicate applePredicate){
            List<Appel> filteredappels= new ArrayList<>();
            for (Appel apple : appels
                 ) {
                if(applePredicate.test(apple)){
                     filteredappels.add(apple);
                }
            }
            return filteredappels;
        }

    private static List<Appel> appels= Arrays.asList(
            new Appel(150,Appel.Color.RED)
                ,new Appel(120,Appel.Color.GREEN)
                ,new Appel(100,Appel.Color.GREEN)
                ,new Appel(90,Appel.Color.RED)
                ,new Appel(180,Appel.Color.RED)
                ,new Appel(170,Appel.Color.GREEN)
        );
    public static List<Appel> FilteringGreenAppels(){
        List<Appel> greenappels= new ArrayList<>();
        for (Appel appel: appels
             ) {
            if(appel.getColor()== Appel.Color.GREEN){
                greenappels.add(appel);
            }
        }
        return greenappels;
    }
    public static List<Appel> FilteringAppelsByWeight(int weight){
        List<Appel> appelsbyweight= new ArrayList<>();
        for (Appel appel: appels
        ) {
            if(appel.getWeight()>=weight){
                appelsbyweight.add(appel);
            }
        }
        return appelsbyweight;
    }
    public static List<Appel> FilteringAppelsByColor(Appel.Color color){
        List<Appel> greenappels= new ArrayList<>();
        for (Appel appel: appels
        ) {
            if(appel.getColor()== color){
                greenappels.add(appel);
            }
        }
        return greenappels;
    }
    public static void main(String[] args) {


        appels.sort(new Comparator<Appel>() {
            //classe anonyme
            @Override
            public int compare(Appel o1, Appel o2) {
                return o1.getWeight()-o2.getWeight();
            }
        });
        for (Appel appel :appels
             ) {
            System.out.println(appel.toString());
        }

        // question 9
        System.out.println("Green appels");
        for (Appel appel : FilteringGreenAppels()
             ) {
            System.out.println(appel.toString());

        }
        for(Appel apple : filter(new Appel.AppleColorPredicate())){
            System.out.println(apple.toString());
        }
        // question 10
        System.out.println("heavy appels");
        for (Appel appel : FilteringAppelsByWeight(150)
        ) {
            System.out.println(appel.toString());

        }
         // question 11

        System.out.println("Red appels");
        for (Appel appel : FilteringAppelsByColor(Appel.Color.RED)
        ) {
            System.out.println(appel.toString());

        }
        System.out.println("Green appels");
        for (Appel appel : FilteringAppelsByColor(Appel.Color.GREEN)
        ) {
            System.out.println(appel.toString());

        }

        for (Appel apple: filter( new AppleColorPredicate())
             ) {
            System.out.println(apple);
        }

        for (Appel apple: filter(new ApplePredicate() {
            @Override
            public boolean test(Appel appel) {
                return appel.getWeight()>=150;

            }})) {
            System.out.println("test"+ apple);

        }
        //for(Appel apple : filter(Appel apple)-> apple.getColor()==Color.RED && apple.getWeight()>=150){
        //}


    }

    }

}


