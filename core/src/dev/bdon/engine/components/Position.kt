//package dev.bdon.engine.components
//
//import dev.bdon.engine.ecs.Id
//import dev.bdon.engine.memory.FloatObjectPool
//
//typealias Position = Int
//    fun Position.getX(pool: FloatObjectPool<Position>) = pool.read(this, 1)
//    fun Position.getY(pool: FloatObjectPool<Position>) = pool.read(this, 2)
//    fun Position.getCenterX(pool: FloatObjectPool<Position>) = pool.read(this, 3)
//    fun Position.getCenterY(pool: FloatObjectPool<Position>) = pool.read(this, 4)
//    fun Position.setX(pool: FloatObjectPool<Position>, value: Float) = pool.write(this, 1, value)
//    fun Position.setY(pool: FloatObjectPool<Position>, value: Float) = pool.write(this, 2, value)
//    fun Position.setCenterX(pool: FloatObjectPool<Position>, value: Float) = pool.write(this, 3, value)
//    fun Position.setCenterY(pool: FloatObjectPool<Position>, value: Float) = pool.write(this, 4, value)