package com.lion.lab.http;

import org.jetbrains.annotations.NotNull;

class Repository {
    private int id;
    private String name;
    private String html_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    @NotNull
    @Override
    public String toString() {
        return "Repository{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", html_url='" + html_url + '\'' +
                '}';
    }
}
