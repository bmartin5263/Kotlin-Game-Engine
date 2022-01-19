import dev.bdon.engine.components.Position
import dev.bdon.engine.components.Render
import dev.bdon.engine.ecs.get
import dev.bdon.engine.entity.entity
import dev.bdon.engine.scene.Scene

class SampleScene : Scene() {

    override fun initialize() {
        val x = entity {

            register<Position> {
                it.x = 2f
            }

            register<Render> {

            }

            onSpawn {
                val pos: Position = it.get()

            }

            script {
                wait(20)
                run {
                    val x = it.get<Position>()
                }
            }
        }
    }
}