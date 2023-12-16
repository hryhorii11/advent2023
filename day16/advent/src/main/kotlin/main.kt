import java.io.File

fun countEnergizedTiles(input: List<String>) {
    val grid = input.map { it.toCharArray() }

    val visited = mutableSetOf<point>()
    val pathList= mutableListOf<Int>()
    fun isValid(x: Int, y: Int): Boolean {
        return x in grid.indices && y in grid[0].indices
    }

    fun traverse(x: Int, y: Int, dx: Int, dy: Int) {
        if (!isValid(y, x) || grid[y][x] == '#') return
        if (grid[y][x] == '.' && visited.contains(point(y, x, dy, dx))) return

        visited.add(point(y, x, dy, dx))

        when (grid[y][x]) {
            '.' -> traverse(x + dx, y + dy, dx, dy)
            '|' -> {
                if (dx != 0) {
                    traverse(x, y + 1, 0, 1)
                    traverse(x, y - 1, 0, -1)
                } else {
                    traverse(x + dx, y + dy, dx, dy)
                }
            }

            '-' -> {
                if (dy != 0) {
                    traverse(x + 1, y, 1, 0)
                    traverse(x - 1, y, -1, 0)
                } else {
                    traverse(x + dx, y + dy, dx, dy)
                }
            }

            '/' -> {
                if (dy == 1) traverse(x - 1, y, -1, 0)
                if (dy == -1) traverse(x + 1, y, 1, 0)
                if (dx == 1) traverse(x, y - 1, 0, -1)
                if (dx == -1) traverse(x, y + 1, 0, 1)
            }

            '\\' -> {
                if (dy == 1) traverse(x + 1, y, 1, 0)
                if (dy == -1) traverse(x - 1, y, -1, 0)
                if (dx == 1) traverse(x, y + 1, 0, 1)
                if (dx == -1) traverse(x, y - 1, 0, -1)
            }
        }
    }
    traverse(0,0,1,0)
    println("taks1:"+visited.distinctBy {it.y to it.x  }.size)
    visited.clear()
    for (i in 0..input[0].length)
    {
        traverse(i, 0, 0, 1)
        pathList.add(visited.distinctBy {it.y to it.x  }.size)
        visited.clear()
        traverse(i,input.size-1,0,-1)
        pathList.add(visited.distinctBy {it.y to it.x  }.size)
        visited.clear()
        traverse(0,i,1,0)
        pathList.add(visited.distinctBy {it.y to it.x  }.size)
        visited.clear()
        traverse(input.size-1,i,-1,0)
        pathList.add(visited.distinctBy {it.y to it.x  }.size)
        visited.clear()
    }
    println("task2:"+pathList.max())

}

fun main() {
    val input = File("inputData.txt").readLines()

   countEnergizedTiles(input)

}

data class point(val y: Int, val x: Int, val dy: Int, val dx: Int)