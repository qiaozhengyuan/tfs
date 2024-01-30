const readline = require('readline-sync')
const { GAME_STATUS, INPUT } = require("./constants")
const Minesweeper = require("./minesweeper")

function main() {
    let size = 3
    let mines = 2
    const s = Number(readline.question(`Enter map size(3): `))
    if (Number.isInteger(s) && s > 1 && s < 50) {
        size = s
        console.log(`You have chosen: ${size}`)
    } else {
        console.log(`Invalid input, using default ${size}`)
    }
    const m = Number(readline.question(`Enter number of mines(2): `))
    if (Number.isInteger(m) && m > 0 && m < size * size) {
        mines = m
        console.log(`You have chosen: ${mines}`)
    } else {
        console.log(`Invalid input, using default ${mines}`)
    }
    const game = new Minesweeper(size, mines)
    let gameStatus = GAME_STATUS.inProgress
    while (![GAME_STATUS.won, GAME_STATUS.lost].includes(gameStatus)) {
        game.printBoard()
        const c = readline.question(`Please provide the coordinates of the cell you would like to uncover(0,0): `)
        if (c === "peek") {
            game.printBoard(true)
        } else {
            const coords = c.split(INPUT.splitter).map(Number).filter(Number.isInteger)
            if (coords.length === 2) {
                console.log(coords)
                gameStatus = game.uncover(...coords)
            } else {
                console.log([0, 0])
                gameStatus = game.uncover(0, 0)
            }
        }
    }
    game.printBoard(true)
    if (gameStatus === GAME_STATUS.won) {
        console.log("You WON! :)")
    } else {
        console.log("You LOST! :(")
    }
}

main()