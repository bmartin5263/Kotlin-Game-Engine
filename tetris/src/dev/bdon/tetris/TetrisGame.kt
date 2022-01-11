package dev.bdon.tetris


import com.bdon.tetris.Tetrino
import dev.bdon.engine.Clock
import dev.bdon.engine.Point
import dev.bdon.engine.entity.Entity
import dev.bdon.engine.entity.interval
import dev.bdon.engine.events.IntervalTimerHandle
import dev.bdon.engine.events.TimerHandle
import dev.bdon.engine.graphics.Graphics
import kotlin.random.Random

class TetrisGame(
    dimensions: Dimensions,
    pos: Point,
    blockSize: Int,
    random: Random,
) : Entity() {

    // Sprites
    private val blockGenerator: TetrinoGenerator = TetrinoGenerator(random)

    private val gameBoard: GameBoard = GameBoard(dimensions)
    val ui: TetrisGameUI = TetrisGameUI(dimensions, pos, blockSize)

    private val spawnCol = dimensions.col / 2 - 1
    private val spawnRow = dimensions.row - 1
    private var row: Int = spawnRow
    private var col: Int = spawnCol
    private var currentTetrino: Tetrino = Tetrinos.L0
    private var dropHandle: IntervalTimerHandle? = null

    override fun initialize() {
        println("TetrisGame::initialize()")
        dropHandle = interval(60) {
            moveDown()
        }
    }

    override fun terminate() {
        println("TetrisGame::terminate()")
    }

    override fun draw(g: Graphics) {
        ui.draw(g)
    }

    private fun spawnNextBlock() {
        row = spawnRow
        col = spawnCol
        currentTetrino = blockGenerator.next()
        ui.updateNextAndAfter(blockGenerator.peekNext(), blockGenerator.peekAfter())
        place(currentTetrino)
    }

    fun moveDown() {
        dropHandle?.reset()
        val success = attemptMove { --row }
        if (!success) {
            gameBoard.clearRows(currentTetrino, Point(col, row))
            ui.boxGrid.updateAll(gameBoard)
            spawnNextBlock()
        }
    }

    fun moveUp() {
        attemptMove { ++row }
    }

    fun moveLeft() {
        attemptMove { --col }
    }

    fun moveRight() {
        attemptMove { ++col }
    }

    fun rotate() {
        attemptMove {
            currentTetrino = currentTetrino.rotate()
        }
    }

    private inline fun attemptMove(operations: TetrisGame.() -> Unit): Boolean {
        val rowSave = row
        val colSave = col
        val tetrinoSave = currentTetrino
        var success = true
        remove(currentTetrino)
        this.operations()
        if (!canPlace(currentTetrino)) {
            row = rowSave
            col = colSave
            currentTetrino = tetrinoSave
            success = false
        }
        place(currentTetrino)
        return success
    }

    private fun place(tetrino: Tetrino) {
        tetrino.body.forEach {
            val y = row + it.y
            val x = col + it.x
            ui.boxGrid.grid[y][x].strokeColor = tetrino.color
        }
        gameBoard.place(tetrino, Point(col, row))
        ui.boxGrid.place(tetrino, Point(col, row))
    }

    private fun canPlace(tetrino: Tetrino): Boolean {
        return gameBoard.canPlace(tetrino, Point(col, row))
    }

    private fun remove(tetrino: Tetrino) {
        gameBoard.remove(tetrino, Point(col, row))
        ui.boxGrid.remove(tetrino, Point(col, row))
    }

    init {
        spawnNextBlock()
    }
}