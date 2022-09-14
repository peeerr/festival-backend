package com.example.board.service;

import com.example.board.DataNotFoundException;
import com.example.board.model.Answer;
import com.example.board.model.Board;
import com.example.board.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer create(Board board, Answer answerForm) {
        Answer answer = new Answer();
        answer.setContent(answerForm.getContent());
        answer.setDate(LocalDateTime.now());
        answer.setNickname(answerForm.getNickname());
        answer.setPassword(answerForm.getPassword());
        answer.setBoard(board);

        answerRepository.save(answer);
        return answer;
    }

    public Answer getAnswer(Long id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        this.answerRepository.save(answer);
    }

    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }

}