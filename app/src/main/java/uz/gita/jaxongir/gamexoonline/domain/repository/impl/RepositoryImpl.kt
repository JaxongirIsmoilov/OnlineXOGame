package uz.gita.jaxongir.gamexoonline.domain.repository.impl

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.jaxongir.gamexoonline.data.common.GameUICommon
import uz.gita.jaxongir.gamexoonline.data.common.UserUICommon
import uz.gita.jaxongir.gamexoonline.data.model.GameModel
import uz.gita.jaxongir.gamexoonline.data.model.UserModel
import uz.gita.jaxongir.gamexoonline.domain.repository.Repository
import uz.gita.jaxongir.gamexoonline.utils.MyLog
import uz.gita.jaxongir.gamexoonline.utils.setNewMap
import java.util.UUID
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {
    override val userDataStateFlow = MutableStateFlow<List<UserUICommon>>(listOf())
    override val gameDataStateFlow = MutableStateFlow<GameUICommon?>(null)
    override val battleFlow = MutableStateFlow<String?>(null)
    private val userPref = Firebase.database.getReference("Players")
    private val gamePref = Firebase.database.getReference("Games")
    private var myId = ""
    private var myName = ""
    private var gameId = ""

    init {
        userPref
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val results = ArrayList<UserUICommon>()
                    snapshot.children.forEach { innerSnapshot ->
                        val uuid = innerSnapshot.key!!
                        val model = innerSnapshot.getValue<UserModel>()
                        if (model != null && model.name != myName && model.painName == "x") {
                            results.add(UserUICommon(uuid, model.name))
                        }
                    }
                    userDataStateFlow.tryEmit(results)
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        gamePref
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    snapshot.children.forEach { innerSnapshot ->
                        val uuid = innerSnapshot.key!!
                        val model = innerSnapshot.getValue<GameModel>()

                        if (model != null && model.secondName == myName) {
                            battleFlow.tryEmit(model.secondName)
                        }

                        if (model != null && (model.firstId == myId || model.secondId == myId)) {
                            ("GameDataStateFlow")
                            gameDataStateFlow.tryEmit(
                                GameUICommon(
                                    uuid,
                                    model.firstId,
                                    model.firstName,
                                    model.firstHod,
                                    model.firstSign,
                                    model.secondId,
                                    model.secondName,
                                    model.secondHod,
                                    model.secondSign,
                                    model.winner,
                                    model.winnerName,
                                    model.indexHod,
                                    model.map,
                                    model.hasWay
                                )
                            )
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }


    override fun addUser(name: String): Flow<Result<Unit>> = callbackFlow {
        MyLog("userName: ${name}")
        val user = UserModel(name)
        val uuid = userPref.push().key ?: UUID.randomUUID().toString()
        myId = uuid
        myName = name
        userPref
            .child(uuid)
            .setValue(user)
            .addOnSuccessListener {
                trySend(Result.success(Unit))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }

        awaitClose()
    }

    override fun attachUsers(pairId: String, pairName: String): Flow<Result<Unit>> = callbackFlow {
        userPref
            .child(myId)
            .setValue(UserModel(myName, pairName))
            .addOnSuccessListener {
                userPref
                    .child(pairId)
                    .setValue(UserModel(pairName, myName))
                    .addOnSuccessListener {
                        val uuid = userPref.push().key ?: UUID.randomUUID().toString()
                        val gameModel = GameModel(
                            uuid,
                            myId,
                            myName,
                            true,
                            "0",
                            pairId,
                            pairName,
                            false,
                            "2",
                            false,
                            ""
                        )
                        gameId = uuid
                        gamePref
                            .child(uuid)
                            .setValue(gameModel)
                            .addOnSuccessListener {
                                trySend(Result.success(Unit))
                            }
                            .addOnFailureListener {
                                trySend(Result.failure(it))
                            }
                    }
            }
        awaitClose()

    }

    override fun updateData(dataUICommon: GameUICommon, newMap: String): Flow<Result<Unit>> =
        callbackFlow {

            gamePref
                .child(dataUICommon.gameId)
                .setValue(dataUICommon.setNewMap(newMap))
                .addOnSuccessListener {
                    trySend(Result.success(Unit))
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }
            awaitClose()
        }

    override fun removeValue() {
        userPref
            .child(myId)
            .removeValue()
    }

    override fun getName(): String = myName
    override fun removeAllData() {
        gamePref.child(gameId).removeValue()
        gameDataStateFlow.value = null
        battleFlow.value = null
    }

}