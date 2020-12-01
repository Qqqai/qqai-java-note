package qqai.suanfa.some;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author qqai
 * @createTime 2020/12/1 21:41
 * @description：动物队列问题
 */

public class AnimalCoding {

    public static void main(String[] args) {
        AnimalCoding animalCoding = new AnimalCoding();
        animalCoding.add(new Dog());
        animalCoding.add(new Cat());
        animalCoding.add(new Cat());
        animalCoding.add(new Cat());
        animalCoding.add(new Cat());
        animalCoding.add(new Cat());
        animalCoding.add(new Cat());
        animalCoding.add(new Dog());
        animalCoding.add(new Dog());
        animalCoding.add(new Dog());
        animalCoding.add(new Dog());
        animalCoding.add(new Dog());

        System.out.println(animalCoding.pollAll());
        System.out.println(animalCoding.pollCat());
    }

    private Queue<PetEnter> dogs;
    private Queue<PetEnter> cats;
    private Long count;

    public AnimalCoding() {
        dogs = new LinkedList<>();
        cats = new LinkedList<>();
        count = 0L;
    }

    public void add(Pet pet) {
        if (pet.getType().equalsIgnoreCase("cat")) {
            cats.add(new PetEnter(pet, count++));
        } else if (pet.getType().equalsIgnoreCase("dog")) {
            dogs.add(new PetEnter(pet, count++));
        } else {
            throw new RuntimeException("err, not dog or cat");
        }
    }

    // 狗出
    public Dog pollDog() {
        if (!this.dogs.isEmpty()) {
            return (Dog) dogs.poll().getPet();
        }
        throw new RuntimeException("dogs is empty");
    }

    // 猫出
    public Cat pollCat() {
        if (!this.cats.isEmpty()) return (Cat) cats.poll().getPet();
        throw new RuntimeException("cats is empty");
    }

    public Pet pollAll() {
        if (cats.isEmpty() && dogs.isEmpty()) throw new RuntimeException("err, queue is empty");
        if (cats.isEmpty() || dogs.isEmpty()) {
            if (cats.isEmpty()) {
                return dogs.poll().getPet();
            } else {
                return cats.poll().getPet();
            }
        } else {
            return dogs.peek().getCount() > cats.peek().getCount() ? dogs.poll().getPet() : cats.poll().getPet();
        }
    }
}

class PetEnter {
    private Pet pet;
    private Long count;

    public PetEnter(Pet pet, Long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

class Cat extends Pet {

    public Cat() {
        super("Cat");
    }
}

class Dog extends Pet {

    public Dog() {
        super("Dog");
    }
}

class Pet {
    private String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "type='" + type + '\'' +
                '}';
    }
}
