package com.yuji.xlt.kotlin

/**
 * out <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/8/12]
 * @since V1.0.0
 */
//todo out 返回输出
interface Production<out T> {
    fun product(): T
}

//todo in 参数输入
interface Consumer<in T> {
    fun consumer(item: T)
}

//todo Invariant
interface ProductionConsumer<T> {
    fun product(): T
    fun consume(item: T)
}

//食品
open class Food

//快餐
open class FastFood : Food()

//汉堡
class Burger : FastFood()

//生产者
//食品商店
class FoodStore : Production<Food> {
    override fun product(): Food {
        println("Produce food.")
        return Food()
    }
}

//快餐商店
class FastFoodStore : Production<FastFood> {
    override fun product(): FastFood {
        println("Produce FastFood.")
        return FastFood()
    }
}

//汉堡商店
class BurgerStore : Production<Burger> {
    override fun product(): Burger {
        println("Produce Burger.")
        return Burger()
    }
}

//消费者1
class EveryBody : Consumer<Food> {
    override fun consumer(item: Food) {
        println("eat food.")
    }
}

//消费者2
class ModernPeople : Consumer<FastFood> {
    override fun consumer(item: FastFood) {
        println("eat fastFood.")
    }
}

//消费者3
class American : Consumer<Burger> {
    override fun consumer(item: Burger) {
        println("eat Burger.")
    }
}

fun main() {
    //todo out 子类泛型赋值给父类泛型
    val production1: Production<Food> = FoodStore()

    /**
     * java中  val production2: Production<Food> = FastFoodStore() 报错，子类不能向上兼容，只能父类向下兼容
     * kotlin val production2: Production<Food> = FastFoodStore() ok,kotlin特性 协变
     * Production<Food> = FastFoodStore() --todo 子类泛型赋值给父类泛型 用out
     */
    val production2: Production<Food> = FastFoodStore()
    val production3: Production<Burger> = BurgerStore()
    production1.product()
    production2.product()


    //todo in 父类泛型对象赋值给子类泛型对象
    val consumer1: Consumer<Burger> = EveryBody()
    val consumer2: Consumer<Burger> = ModernPeople()
    val consumer3: Consumer<Burger> = American()
    consumer1.consumer(Burger())
}