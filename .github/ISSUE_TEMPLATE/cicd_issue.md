---
name: CI/CD Issue
about: Report deployment or pipeline issues
title: '[CI/CD] '
labels: 'ci/cd, bug'
assignees: ''

---

**Pipeline/Deployment Issue**
Describe what went wrong with the CI/CD pipeline or deployment.

**Build/Deployment Logs**
```
Paste relevant build or deployment logs here
```

**Environment**
- [ ] Development
- [ ] Staging
- [ ] Production

**Pipeline Stage**
- [ ] Build
- [ ] Test
- [ ] Docker Build
- [ ] Docker Push
- [ ] Deployment
- [ ] Health Check

**Expected vs Actual**
**Expected:** What should have happened
**Actual:** What actually happened

**Docker/Container Details**
- Image: [e.g. ecom-microservices:latest]
- Port mapping: [e.g. 8080:8080]
- Environment variables: [list any relevant env vars]

**Reproduction Steps**
1. Step 1
2. Step 2
3. Step 3

**Quick Fix Attempted**
- [ ] Rerun pipeline
- [ ] Check container logs
- [ ] Restart services
- [ ] Other: ________

**Additional Context**
Add any other context about the CI/CD issue here.