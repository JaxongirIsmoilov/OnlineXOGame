package uz.gita.jaxongir.gamexoonline.utils

import timber.log.Timber
import uz.gita.jaxongir.gamexoonline.data.common.GameUICommon


fun MyLog(message: String) {
    Timber.tag("TTT").d(message)
}

fun GameUICommon.setNewMap(newMap: String): GameUICommon =
    GameUICommon(
        gameId,
        firstId,
        firstName,
        firstHod,
        firstSign,
        secondId,
        secondName,
        secondHod,
        secondSign,
        winner,
        winnerName,
        indexHod,
        newMap,
        !hasWay
    )

fun GameUICommon.setWinner(name: String): GameUICommon =
    GameUICommon(
        gameId,
        firstId,
        firstName,
        firstHod,
        firstSign,
        secondId,
        secondName,
        secondHod,
        secondSign,
        true,
        name,
        indexHod,
        map,
        hasWay
    )