const { GAME_STATUS } = require("./constants")

class Minesweeper {
    constructor(size, mines) {
        this.size = size
        this.mines = new Set()
        this.board = Array.from({ length: size }, () => Array(size).fill(' '))
        this.covered = new Set()

        // Populate the covered set
        for (let i = 0; i < size; i++) {
            for (let j = 0; j < size; j++) {
                this.covered.add(`${i},${j}`)
            }
        }

        // Place mines randomly
        while (this.mines.size < mines) {
            const x = Math.floor(Math.random() * size)
            const y = Math.floor(Math.random() * size)
            const key = `${x},${y}`
            if (!this.mines.has(key)) {
                this.mines.add(key)
                this.board[x][y] = 'M'
            }
        }

        // Calculate numbers for each cell
        for (let key of this.mines) {
            const [x, y] = key.split(',').map(Number)
            for (let i = Math.max(0, x - 1); i <= Math.min(size - 1, x + 1); i++) {
                for (let j = Math.max(0, y - 1); j <= Math.min(size - 1, y + 1); j++) {
                    if (!this.mines.has(`${i},${j}`)) {
                        this.board[i][j] = this.board[i][j] === ' ' ? '1' : (parseInt(this.board[i][j]) + 1).toString()
                    }
                }
            }
        }
    }

    printBoard(reveal = false) {
        for (let i = 0; i < this.size; i++) {
            let row = ''
            for (let j = 0; j < this.size; j++) {
                if (reveal) {
                    row += `${this.board[i][j]} `
                } else {
                    row += this.covered.has(`${i},${j}`) ? 'X ' : `${this.board[i][j]} `
                }
            }
            console.log(row)
        }
        console.log()
    }

    uncover(x, y) {
        const key = `${x},${y}`
        if (x < 0 || x >= this.size || y < 0 || y >= this.size || !this.covered.has(key)) {
            return GAME_STATUS.invalid
        }

        this.covered.delete(key)

        if (this.board[x][y] === 'M') {
            return GAME_STATUS.lost
        } else if (this.board[x][y] === ' ') {
            // Uncover adjacent cells
            for (let i = Math.max(0, x - 1); i <= Math.min(this.size - 1, x + 1); i++) {
                for (let j = Math.max(0, y - 1); j <= Math.min(this.size - 1, y + 1); j++) {
                    if (this.covered.has(`${i},${j}`)) {
                        this.uncover(i, j)
                    }
                }
            }
        }

        return this.covered.size === this.mines.size ? GAME_STATUS.won : GAME_STATUS.inProgress
    }
}

module.exports = Minesweeper