package chrisservice.project.chucknorrisjokes.model;

import java.util.List;

public class GetJokesResponse {

    private List<Joke> value;

    public GetJokesResponse() {
    }

    public GetJokesResponse(List<Joke> value) {
        this.value = value;
    }

    public List<Joke> getValue() {
        return value;
    }

    public void setValue(List<Joke> value) {
        this.value = value;
    }
}
