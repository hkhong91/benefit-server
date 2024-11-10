package com.example.benefit.domain.component.point.user.infrastructure

import com.example.benefit.domain.component.point.user.document.UserPointTx
import org.springframework.data.mongodb.repository.MongoRepository

interface UserPointTxRepository : MongoRepository<UserPointTx, Long>