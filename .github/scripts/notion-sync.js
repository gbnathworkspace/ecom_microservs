const { Client } = require('@notionhq/client');

const notion = new Client({
  auth: process.env.NOTION_TOKEN,
});

async function syncCommitToNotion() {
  const commitSha = process.env.GITHUB_SHA;
  const commitMessage = process.env.COMMIT_MESSAGE;
  const repoName = process.env.GITHUB_REPOSITORY;
  const commitUrl = `https://github.com/${repoName}/commit/${commitSha}`;
  
  try {
    await notion.pages.create({
      parent: {
        database_id: process.env.NOTION_DATABASE_ID,
      },
      properties: {
        'Commit': {
          title: [
            {
              text: {
                content: commitMessage || 'No commit message'
              }
            }
          ]
        },
        'SHA': {
          rich_text: [
            {
              text: {
                content: commitSha.substring(0, 7)
              }
            }
          ]
        },
        'URL': {
          url: commitUrl
        },
        'Repository': {
          rich_text: [
            {
              text: {
                content: repoName
              }
            }
          ]
        },
        'Date': {
          date: {
            start: new Date().toISOString()
          }
        }
      }
    });
    
    console.log('✅ Commit synced to Notion successfully');
  } catch (error) {
    console.error('❌ Error syncing to Notion:', error.message);
    process.exit(1);
  }
}

syncCommitToNotion();